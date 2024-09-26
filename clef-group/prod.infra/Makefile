.PHONY: env
env-desc = "Setup local dev env"
env:
	@echo "==> $(env-desc)"

	@[ -d "${PWD}/.direnv" ] || (echo "Venv not found: ${PWD}/.direnv" && exit 1)
	@pip3 install -U pip wheel setuptools --no-cache-dir && \
	echo "[  OK  ] PIP + WHEEL + SETUPTOOLS" || \
	echo "[FAILED] PIP + WHEEL + SETUPTOOLS"

	@pip3 install -U --no-cache-dir -r "${PWD}/requirements.txt" && \
	echo "[  OK  ] PIP REQUIREMENTS" || \
	echo "[FAILED] PIP REQUIREMENTS"
	
	@ansible-galaxy install --force -r "${PWD}/requirements.yml" && \
	echo "[  OK  ] ANSIBLE-GALAXY REQUIREMENTS" || \
	echo "[FAILED] ANSIBLE-GALAXY REQUIREMENTS"

	@echo '#\n# cat ./ssh.cfg.d/*\n#' > ssh.cfg; cat ./ssh.cfg.d/* >> ssh.cfg && \
	echo "[  OK  ] SSH_CONFIG" || \
	echo "[FAILED] SSH_CONFIG"

