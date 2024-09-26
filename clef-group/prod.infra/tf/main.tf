resource "lxd_profile" "prod_storage" {
  name        = "prod_storage"
  description = "profile pour l'utilisation de stockage default pour la production"

  config = {
    "user.vendor-data" = <<EOT
#cloud-config
packages:
  - openssh-server
  - dnsutils
users:
  - default
  - name: ubuntu
    sudo: ALL=(ALL) NOPASSWD:ALL
    ssh_authorized_keys: 
    - ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDjw2YEVM4Srco21itEV8NYcBQVPBa+vgtyoYY7wAndDm+/y7X0MdbplnJ4/+YN+k+JZQ/hcc5Ytnx0xbklutToy8zSdtlP8LWqwko17LIkFKnyjaHiCvn4Al3ak1Ze0fxO4cxNy7inuXQFsYcgzHHffDt+uGUuuJOqlv2hMyVOMUo21uWSGrJkdOAPWN4E/MO7b+hyaU9jZ9W9CuAVPvslRKq97lKTfPN56Kkad/fcO8qFfuDWzWwe4bXrV4HXWkrGcwxqsV9m266k75itXqo8QioE8O+LyxQsZh/5TILQjMeUSdq5D74lk8pp+IVfrvEybjKCi3vtO0Zq6VCmGoKiUoRVRA+jsnwYxrIjlUQBVHqXZ2CryTCQXLsJVnkKlmdjOZGA2cUOS/Q+A6xAXqcItNcu4+m3v6wJkN6bbWgZ38bmb9FM6CDBsJa9YEejv+FHh/lOU1jICBJaKbvdcEA+RfI0F2vpx6i7a/AIGMfiijCx9oagmLcT74rr6bhPHd8= paivacd@ubuntu-usb
EOT

  }

  device {
    type = "disk"
    name = "root"

    properties = {
      pool = "default"
      path = "/"
    }
  }
}


resource "lxd_network" "prod_network" {
  name = "prod_network"

  config = {
    "ipv4.address" = "192.168.123.1/24"
    "ipv4.nat"     = "true"
    "ipv6.address" = "none"
  }

}

resource "lxd_profile" "prod_network" {
  name = "prod_network"

  device {
    name = "eth0"
    type = "nic"

    properties = {
      nictype = "bridged"
      parent  = "${lxd_network.prod_network.name}"
    }
  }

}

resource "lxd_container" "prod_srvapp" {
  name      = "prod-srvapp"
  image     = "images:ubuntu/20.04/cloud"
  ephemeral = false
  profiles  = ["${lxd_profile.prod_storage.name}", "${lxd_profile.prod_network.name}"]

  config = {
    "user.access_interface" = "eth0"


    "user.network-config" = <<EOT
#cloud-config
version: 2
ethernets:
  eth0:
    addresses: 
      - 192.168.123.10/24
    gateway4: 192.168.123.1
    nameservers:
      addresses: 
        - 192.168.123.1
EOT      
  }

  provisioner "remote-exec" {
    inline = ["echo 'Hello world!'"]
    connection {
      type = "ssh"
      user = "ubuntu"
      host = self.ip_address
      #      bastion_host = var.machine.ssh_bastion != null ? var.machine.ssh_bastion.host : ""
      #      bastion_user = var.machine.ssh_bastion != null ? var.machine.ssh_bastion.user : ""
    }
  }

}

resource "local_file" "prod_srvapp_ssh_config" {

  filename = "../ssh.cfg.d/terraform-${lxd_container.prod_srvapp.name}.cfg"
  content = templatefile("${path.module}/templates/ssh_config.tftpl", {
    host     = lxd_container.prod_srvapp.name,
    hostname = lxd_container.prod_srvapp.ip_address,
    ssh_user = "ubuntu",
  })
}

resource "lxd_container" "prod_db" {
  name      = "prod-db"
  image     = "images:ubuntu/20.04/cloud"
  ephemeral = false
  profiles  = ["${lxd_profile.prod_storage.name}", "${lxd_profile.prod_network.name}"]

  config = {
    "user.access_interface" = "eth0"

    "user.network-config" = <<EOT
#cloud-config
version: 2
ethernets:
  eth0:
    addresses: 
      - 192.168.123.20/24
    gateway4: 192.168.123.1
    nameservers:
      addresses: 
        - 192.168.123.1
EOT      

  }


  provisioner "remote-exec" {
    inline = ["echo 'Hello world!'"]
    connection {
      type = "ssh"
      user = "ubuntu"
      host = self.ip_address
      #      bastion_host = var.machine.ssh_bastion != null ? var.machine.ssh_bastion.host : ""
      #      bastion_user = var.machine.ssh_bastion != null ? var.machine.ssh_bastion.user : ""
    }
  }

}

resource "local_file" "prod_db_ssh_config" {

  filename = "../ssh.cfg.d/terraform-${lxd_container.prod_db.name}.cfg"
  content = templatefile("${path.module}/templates/ssh_config.tftpl", {
    host     = lxd_container.prod_db.name,
    hostname = lxd_container.prod_db.ip_address,
    ssh_user = "ubuntu",
  })
}

resource "null_resource" "ssh_cfg" {

  depends_on = [local_file.prod_srvapp_ssh_config, local_file.prod_db_ssh_config ]

  provisioner "local-exec" {
    working_dir = ".."
    interpreter = ["/bin/bash", "-c"]
    command     = "echo '#\n# cat ./ssh.cfg.d/*\n#' > ssh.cfg; cat ./ssh.cfg.d/* >> ssh.cfg"
  }
}

resource "null_resource" "prod_srvapp_ansible_playbook" {

  depends_on = [null_resource.ssh_cfg]

  provisioner "local-exec" {
    working_dir = ".."
    interpreter = ["/bin/bash", "-c"]
    command     = "ansible-playbook playbooks/playbook.yml -l ${lxd_container.prod_srvapp.name} -v"
  }

}

resource "null_resource" "prod_db_ansible_playbook" {

  depends_on = [null_resource.ssh_cfg]

  provisioner "local-exec" {
    working_dir = ".."
    interpreter = ["/bin/bash", "-c"]
    command     = "ansible-playbook playbooks/playbook.yml -l ${lxd_container.prod_db.name} -v"
  }

}

