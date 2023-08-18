# RDF Description

### The connector sends the following information to the Possible API via RDF



- `edcApiVersion` this information is from the [config.properties file](https://github.com/POSSIBLE-X/possible-x-edc-extension/blob/main/connector/resources/config.properties)

- `Asset ID` - this information is from the [step 1](https://github.com/POSSIBLE-X/possible-x-edc-extension/tree/main/readme.md#1-asset-creation-for-the-consumer) 
- `Asset Name` - this information is from the [step 1](https://github.com/POSSIBLE-X/possible-x-edc-extension/tree/main/readme.md#1-asset-creation-for-the-consumer) 
- `Asset Description` - this information is from the [step 1](https://github.com/POSSIBLE-X/possible-x-edc-extension/tree/main/readme.md#1-asset-creation-for-the-consumer) 
- `Protocol ID` - this information is from the [step 2](https://github.com/POSSIBLE-X/possible-x-edc-extension/tree/main/readme.md#2-policy-creation)
- `ContractOfferId` - this information is from the [step 3](https://github.com/POSSIBLE-X/possible-x-edc-extension/tree/main/readme.md#3-contract-creation) 


### Jena Handler information crossover

| Connector variables                          | RDF                                                      |
|-----------------------------------------|------------------------------------------------------------------|
| `edcApiVersion` | possible-x:edcApiVersion 
| `Asset ID`                    | possible-x:assetId      |
| `Asset Name`             | dct:title |
| `Asset Description`             | dct:description connector |
| `Protocol ID`             |  possible-x:uid |
| `ContractOffer ID`             | possible-x:contractOfferId  |


### EXAMPLE
```@prefix dcat: <http://www.w3.org/ns/dcat#> .
@prefix dct:<http://purl.org/dc/terms/> . 
@prefix gax-core: <http://w3id.org/gaia-x/core#> . 
@prefix gax-trust-framework: <http://w3id.org/gaia-x/gax-trust-framework#> . 
@prefix possible-x:          <http://w3id.org/gaia-x/possible-x#> . 
@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> . 
			<https://possible.fokus.fraunhofer.de/set/distribution/1> 
			a               
			dcat:distribution ;         
			dct:license     <http://dcat-ap.de/def/licenses/gfdl> ;         
			dcat:accessURL  <http://xxxxxxxx:9192/api/v1/data/assets/test-document_company2> .  
			<https://possible.fokus.fraunhofer.de/set/data/test-dataset a   gax-trust-framework:DataResource , 
			dcat:Dataset ;         
			dct:description                 "With this dataset you can do this and that"@en ;         
			dct:title                       "MultiInst Dataset"@en ;         
			gax-trust-framework:containsPII                 false ;         
			gax-trust-framework:exposedThrough                 <http://xxxxxxxx:8282/> ;         
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
 <https://possible.fokus.fraunhofer.de/set/distribution/1>```