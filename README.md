# POSSIBLE Extension

This document explains how to run an EDC with the `POSSIBLE-X` and the `IONOS S3` extensions.

## Requirements

- java 17
- gradle

## Steps
### Checkout the repo

- Checkout this repository
```
git clone  https://github.com/POSSIBLE-X/possible-x-edc-extension.git
```

### Compiling

- export your `GitHub` authentication data
```
export USERNAME_GITHUB=<YOUR USERNAME> or <YOUR TOKEN NAME>
export TOKEN_GITHUB=<YOUR TOKEN>
```
- go to your the main folder and execute the following:
```
./gradlew build
```

### Edit config file

- Open the `connector/resources/config.properties` file and edit the following fields:  

| Field name                      | Description                                                |
|---------------------------------|------------------------------------------------------------|
| `possible.catalog.jwt.token`    | Authorization token to access the Possible-X Catalog       |
| `possible.catalog.endpoint`     | Endpoint of the Possible-X Catalog for the SD registration |
| `possible.connector.edcVersion` | Version of the Connector                                   |
| `edc.ionos.access.key`          | IONOS Access Key Id to access S3                           |
| `edc.ionos.secret.key`          | IONOS Secret Access Key to access S3                       |
| `edc.ionos.endpoint`            | IONOS S3 Endpoint                                          |
| `edc.ionos.token`               | IONOS token to allow S3 provisioning                       |

To know more the `IONOS S3 Extension` please check this [site](https://github.com/ionos-cloud/edc-ionos-s3).


### Running

- Execute the following command:
```
java -Dedc.fs.config=connector/resources/config.properties  -jar connector/build/libs/connector.jar
```  

### Interacting


## Usage


We will have to call some URL's in order to transfer the file:

1) Asset creation for the consumer
- @id (you can put whatever you want, will be use one step 3)
- name (will be sent to the Possible api)
- description (will be sent to the Possible api)


```
 curl -d '{
           "@context": {
             "edc": "https://w3id.org/edc/v0.0.1/ns/"
           },
           "asset": {
             "@id": "hackathonDataSet",
             "properties": {
               "name": "MultiInst Dataset",
               "description": "With this dataset you can do this and that",
               "contenttype": "application/json"
             }
           },
           "dataAddress": {
             "type": "IonosS3",
			 "storage": "s3-eu-central-1.ionoscloud.com",
             "bucketName": "company1",
             "keyName" : "device1-data.csv"
           }
         }' \
  -H 'X-API-Key: password' -H 'content-type: application/json' http://localhost:8182/management/v2/assets
  ```


### 2) Policy creation
- @id (You can put whatever you want, will be use one step 3)

```
curl -d '{
           "@context": {
             "edc": "https://w3id.org/edc/v0.0.1/ns/",
             "odrl": "http://www.w3.org/ns/odrl/2/"
           },
           "@id": "aPolicy1",
           "policy": {
             "@type": "set",
             "odrl:permission": [],
             "odrl:prohibition": [],
             "odrl:obligation": []
           }
         }' \
  -H 'X-API-Key: password' -H 'content-type: application/json' http://localhost:8182/management/v2/policydefinitions

```


### 3) Contract creation

- @id: (You can put whatever you want)
- accessPolicyId: (It's the @ID you inserted in step 2 )
- contractPolicyId: (It's the @ID you inserted in step 2 )
- operandRight: (It's the @ID you inserted in step 1)
```
  curl -d '{
           "@context": {
             "edc": "https://w3id.org/edc/v0.0.1/ns/"
           },
           "@id": "igeneratemypolicyid",
           "accessPolicyId": "aPolicy1",
           "contractPolicyId": "aPolicy1",
           "assetsSelector": [
              {"operandLeft":"https://w3id.org/edc/v0.0.1/ns/id", "operator":"=", "operandRight":"hackathonDataSet"}
           ]
         }' \
  -H 'X-API-Key: password' -H 'content-type: application/json' http://localhost:8182/management/v2/contractdefinitions
```

Now you have associated the asset (step 1) with the policy (step 2) and the Possible Connector sends automatically data to the Possible API :
- asset @id (step 1) = possible-x:assetId
- asset name (step 1) = dct:title
- asset description (step 1) =dct:description
- policy @id (step 2) = possible-x:uid
- contractDefinition @id (step 3)= possible-x:contractOfferId

[more details](https://github.com/POSSIBLE-X/possible-x-edc-extension/blob/main/extension/RDF.md)

```
@prefix dcat: <http://www.w3.org/ns/dcat#> .
@prefix dct:<http://purl.org/dc/terms/> . 
@prefix gax-core: <http://w3id.org/gaia-x/core#> . 
@prefix gax-trust-framework: <http://w3id.org/gaia-x/gax-trust-framework#> . 
@prefix possible-x:          <http://w3id.org/gaia-x/possible-x#> . 
@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> . 
			<https://possible.fokus.fraunhofer.de/set/distribution/1> 
			a               
			dcat:distribution ;         
			dct:license     <http://dcat-ap.de/def/licenses/gfdl> ;         
			dcat:accessURL  <xxxxxxx> .  
			<https://possible.fokus.fraunhofer.de/set/data/test-dataset a   gax-trust-framework:DataResource , 
			dcat:Dataset ;         
			dct:description                 "With this dataset you can do this and that"@en ;         
			dct:title                       "MultiInst Dataset"@en ;         
			gax-trust-framework:containsPII                 false ;         
			gax-trust-framework:exposedThrough                 <xxxxxx> ;         
			gax-trust-framework:producedBy  <https://piveau.io/set/resource/legal-person/some-legal-person-2> ;         
			possible-x:assetId              "hackathonDataSet" ;
			possible-x:contractOfferId      "igeneratemypolicyid" ;
			possible-x:edcApiVersion        "0.1.2" ;
			possible-x:hasPolicy            [ a                          possible-x:Policy ;
			possible-x:hasPermissions  [ a                   possible-x:Permission ;
            possible-x:action   possible-x:Use ;
			possible-x:edcType  "dataspaceconnector:permission" ;
			possible-x:target   "hackathonDataSet"
                               ] ;
			possible-x:policyType      possible-x:Set ;
            possible-x:uid             "aPolicy1"
                           ] ;         possible-x:protocol             possible-x:IdsMultipart ;         dcat:distribution
 <https://possible.fokus.fraunhofer.de/set/distribution/1>
```

###  4) Fetching the catalog

```
curl -X POST "http://localhost:9192/management/v2/catalog/request" \
    -H 'Content-Type: application/json' \
    -H 'X-API-Key: password' \
    -d '{
      "@context": {
        "edc": "https://w3id.org/edc/v0.0.1/ns/"
      },
      "providerUrl": "http://localhost:8282/protocol",
      "protocol": "dataspace-protocol-http"
    }'
```

you will receive a result similar to this:

```
result: 
 {
	"@id": "9276d7ac-d752-499d-8dca-69803d5b6eba",
	"@type": "dcat:Catalog",
	"dcat:dataset": {
		"@id": "414754e6-df91-4ab8-b230-3881ab062a27",
		"@type": "dcat:Dataset",
		"odrl:hasPolicy": [
			{
				"@id": "1234:hackathonDataSet:d59d7555-5495-4e6a-8f61-f31c73629d11",
				"@type": "odrl:Set",
				"odrl:permission": [],
				"odrl:prohibition": [],
				"odrl:obligation": [],
				"odrl:target": "hackathonDataSet"
			},
			{
				"@id": "igeneratemypolicyid:hackathonDataSet:2df6d095-600b-4212-ae99-7e337254b2a4",
				"@type": "odrl:Set",
				"odrl:permission": [],
				"odrl:prohibition": [],
				"odrl:obligation": [],
				"odrl:target": "hackathonDataSet"
			}
		],
		"dcat:distribution": [],
		"edc:name": "MultiInst Dataset",
		"edc:description": "With this dataset you can do this and that",
		"edc:id": "hackathonDataSet",
		"edc:contenttype": "application/json"
	},
	"dcat:service": {
		"@id": "af36b239-2e5f-451e-92d8-0740366874b3",
		"@type": "dcat:DataService",
		"dct:terms": "connector",
		"dct:endpointUrl": "http://localhost:8282/protocol"
	},
	"edc:participantId": "possible",
	"@context": {
		"dct": "https://purl.org/dc/terms/",
		"edc": "https://w3id.org/edc/v0.0.1/ns/",
		"dcat": "https://www.w3.org/ns/dcat/",
		"odrl": "http://www.w3.org/ns/odrl/2/",
		"dspace": "https://w3id.org/dspace/v0.8/"
	}
}
``` 

If you see odrl:hasPolicy: you will have this dataSet:
```
"odrl:hasPolicy": [.....,
{
				"@id": "igeneratemypolicyid:hackathonDataSet:2df6d095-600b-4212-ae99-7e337254b2a4",
				"@type": "odrl:Set",
				"odrl:permission": [],
				"odrl:prohibition": [],
				"odrl:obligation": [],
				"odrl:target": "hackathonDataSet"
			}
]
```

you will be seen on id "@id": "igeneratemypolicyid:hackathonDataSet:2df6d095-600b-4212-ae99-7e337254b2a4"

- igeneratemypolicyid - is equal step 3 @id
- hackathonDataSet - id from asset step 1 @id
- 2df6d095-600b-4212-ae99-7e337254b2a4 - is generated by the EDC

Now you will see the asset on Possible catalog

https://possible.fokus.fraunhofer.de/datasets?locale=en