.DEFAULT_GOAL := help

PHONY: help

help: ## Print help message:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'


zepelin:  ## Run Zeppelin
	docker run \
		-p 8080:8080 --rm \
        --name zeppelin apache/zeppelin:0.10.0
