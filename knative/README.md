## Steps to create knative services ##

- oc new-product cloudi
- oc apply -f kn-svc-deploy-developer-rolebinding.yaml (by cluster admin)
- kn service create product --image YOUR_DOCKER-IMAGE
- kn service list, or 
- oc get ksvc



 
