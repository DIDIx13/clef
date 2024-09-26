# Mise en place de l'infrastructure

amelida@ubuntu-usb:~/undergit/clef-group$ sudo apt install direnv make python3-pip python3-venv -y

amelida@ubuntu-usb:~/undergit/clef-group$ echo 'eval "$(direnv hook bash)" ' >> ~/.bashrc 
amelida@ubuntu-usb:~/undergit/clef-group$ cat ~/.bashrc 
...
```
# enable programmable completion features (you don't need to enable
# this, if it's already enabled in /etc/bash.bashrc and /etc/profile
# sources /etc/bash.bashrc).
if ! shopt -oq posix; then
  if [ -f /usr/share/bash-completion/bash_completion ]; then
    . /usr/share/bash-completion/bash_completion
  elif [ -f /etc/bash_completion ]; then
    . /etc/bash_completion
  fi
fi

# PostgreSQL env var
PATH=$PATH:/usr/lib/postgresql/14/bin
export PATH
export PGDATA="/var/lib/postgresql/14/main"

# Git alias
alias gitend='rm temp.md && git add . && git commit -m"#END"'
alias gitstart='touch temp.md && git add . && git commit -m"#START"'
eval "$(direnv hook bash)" 
```
amelida@ubuntu-usb:~/undergit/clef-group/prod.infra$ source ~/.bashrc 
direnv: error /home/amelida/undergit/clef-group/prod.infra/.envrc is blocked. Run `direnv allow` to approve its content
amelida@ubuntu-usb:~/undergit/clef-group/prod.infra$ direnv allow

amelida@ubuntu-usb:~/undergit/clef-group/prod.infra$ make
