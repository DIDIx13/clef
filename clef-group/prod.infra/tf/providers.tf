terraform {
  required_providers {
    lxd = {
      source  = "terraform-lxd/lxd"
      version = "1.8.0"
    }
  }
}

provider "lxd" {
  generate_client_certificates = false
  accept_remote_certificate    = true

  #  lxd_remote {
  #    name    = "labo-cluster"
  #    scheme  = "https"
  #    address = "157.26.173.87"
  #    default = true
  #  }
}

