pushd microservice-parent	& call  mvn -q clean install  & popd
pushd config-server			& call  mvn -q clean install  & popd
pushd discovery-service 	& call  mvn -q clean install  & popd
pushd AuthServer 			& call  mvn -q clean install  & popd
pushd organ-service 		& call 	mvn -q clean install  & popd
pushd slide-service 		& call  mvn -q clean install  & popd
