//Data model for Nuclei

Cypher

// Clear the database (delete all nodes and relationships)
{"statements":[{"statement":"match (n) optional match (n)-[r]-() delete n, r"}]}

// create taxonomy
{"statements":[{"statement":"CREATE(n:Taxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":10001, "name":"BOSH", "classType":"Interface Type", "version":"2.2","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Taxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":10002, "name":"Iaas", "classType":"AWS", "version":"0.0","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Taxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":10003, "name":"order lifecycle", "classType":"draft order", "version":"0.0","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Taxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":10004, "name":"order lifecycle", "classType":"ready for provisioning", "version":"0.0","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Taxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":10005, "name":"order lifecycle", "classType":"order provisioned", "version":"0.0","isDeleted":"0"}}}]}

//create IaaSTemplate
{"statements":[{"statement":"CREATE(n:IaaSTemplate{props})RETURN id(n)", "parameters":{"props":{"UUID":20001, "IaasName":"Iaas Template 1","isDeleted":"0"}}}]}

//create TemplateFunction
{"statements":[{"statement":"CREATE(n:TemplateFunction{props})RETURN id(n)", "parameters":{"props":{"UUID":30001, "functionName":"bosh target","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:TemplateFunction{props})RETURN id(n)", "parameters":{"props":{"UUID":30002, "functionName":"bosh login","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:TemplateFunction{props})RETURN id(n)", "parameters":{"props":{"UUID":30003, "functionName":"bosh upload stemcell","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:TemplateFunction{props})RETURN id(n)", "parameters":{"props":{"UUID":30004, "functionName":"bosh upload release","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:TemplateFunction{props})RETURN id(n)", "parameters":{"props":{"UUID":30005, "functionName":"bosh deployment","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:TemplateFunction{props})RETURN id(n)", "parameters":{"props":{"UUID":30006, "functionName":"bosh deploy","isDeleted":"0"}}}]}

//create Blueprint
{"statements":[{"statement":"CREATE(n:Blueprint{props})RETURN id(n)", "parameters":{"props":{"UUID":40001, "blueprint":"new mysql server", "version":"v1.1","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Blueprint{props})RETURN id(n)", "parameters":{"props":{"UUID":40002, "blueprint":"new mysql server", "version":"v2.3","isDeleted":"0"}}}]}

//create Tasks
{"statements":[{"statement":"CREATE(n:Tasks{props})RETURN id(n)", "parameters":{"props":{"UUID":50001, "task":"release","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Tasks{props})RETURN id(n)", "parameters":{"props":{"UUID":50002, "task":"compilation","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Tasks{props})RETURN id(n)", "parameters":{"props":{"UUID":50003, "task":"update","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Tasks{props})RETURN id(n)", "parameters":{"props":{"UUID":50004, "task":"networks","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Tasks{props})RETURN id(n)", "parameters":{"props":{"UUID":50005, "task":"resource_pools","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Tasks{props})RETURN id(n)", "parameters":{"props":{"UUID":50006, "task":"cloud","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:Tasks{props})RETURN id(n)", "parameters":{"props":{"UUID":50007, "task":"jobs","isDeleted":"0"}}}]}

//ServiceCatalogue
{"statements":[{"statement":"CREATE(n:ServiceCatalogue{props})RETURN id(n)", "parameters":{"props":{"UUID":60001, "Template":"mySQL Server","isDeleted":"0"}}}]}

//ServiceCatalogueVersion
{"statements":[{"statement":"CREATE(n:ServiceCatalogueVersion{props})RETURN id(n)", "parameters":{"props":{"UUID":70001, "version":"4.1","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:ServiceCatalogueVersion{props})RETURN id(n)", "parameters":{"props":{"UUID":70002, "version":"5.7","isDeleted":"0"}}}]}

//ScTaxonomy
{"statements":[{"statement":"CREATE(n:ScTaxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":80001, "taxName":"mysql server", "version":"5.7","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:ScTaxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":80002, "taxName":"OS", "value":"RHEL", "version":"6.1","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:ScTaxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":80003, "taxName":"Patch", "value":"RHEL", "version":"patch 1","isDeleted":"0"}}}]}
{"statements":[{"statement":"CREATE(n:ScTaxonomy{props})RETURN id(n)", "parameters":{"props":{"UUID":80004, "taxName":"mysql server", "version":"4.1","isDeleted":"0"}}}]}

//User
{"statements":[{"statement":"CREATE(n:User{props})RETURN id(n)", "parameters":{"props":{"UUID":90001, "email":"jmalan69@gmail.com","isDelete":"false"}}}]}

//Role
{"statements":[{"statement":"CREATE(n:Role{props})RETURN id(n)", "parameters":{"props":{"UUID":100001, "name":"User","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:Role{props})RETURN id(n)", "parameters":{"props":{"UUID":100002, "name":"Administrator","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:Role{props})RETURN id(n)", "parameters":{"props":{"UUID":100003, "name":"Super user","isDelete":"false"}}}]}

//Metadata
{"statements":[{"statement":"CREATE(n:Metadata{props})RETURN id(n)", "parameters":{"props":{"UUID":110001, "name":"Google"}}}]}
{"statements":[{"statement":"CREATE(n:Metadata{props})RETURN id(n)", "parameters":{"props":{"UUID":110002, "name":"Linkedin"}}}]}

//SecurityTags
{"statements":[{"statement":"CREATE(n:SecurityTags{props})RETURN id(n)", "parameters":{"props":{"UUID":120001, "name":"Provision up to $200","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:SecurityTags{props})RETURN id(n)", "parameters":{"props":{"UUID":120002, "name":"data classification A","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:SecurityTags{props})RETURN id(n)", "parameters":{"props":{"UUID":120003, "name":"Provision up to $5000","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:SecurityTags{props})RETURN id(n)", "parameters":{"props":{"UUID":120004, "name":"data classification B","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:SecurityTags{props})RETURN id(n)", "parameters":{"props":{"UUID":120005, "name":"Edit Templates","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:SecurityTags{props})RETURN id(n)", "parameters":{"props":{"UUID":120006, "name":"Edit Blueprints","isDelete":"false"}}}]}
{"statements":[{"statement":"CREATE(n:SecurityTags{props})RETURN id(n)", "parameters":{"props":{"UUID":120007, "name":"data classification c","isDelete":"false"}}}]}

//Client
{"statements":[{"statement":"CREATE(n:Client{props})RETURN id(n)", "parameters":{"props":{"UUID":130001, "Client":"Telstra"}}}]}

//Order
{"statements":[{"statement":"CREATE(n:Order{props})RETURN id(n)", "parameters":{"props":{"UUID":140001, "History":"Order 2233", "instance_type":"monitoring", "version":"1.2.13"}}}]}

//IaaSConfig
{"statements":[{"statement":"CREATE(n:IaaSConfig{props})RETURN id(n)", "parameters":{"props":{"UUID":150001, "name":"IaaSConfig 1"}}}]}

//Deployment
{"statements":[{"statement":"CREATE(n:Deployment{props})RETURN id(n)", "parameters":{"props":{"UUID":160001, "name":"jack_mysql"}}}]}


//create node Relationships

//client to order
{"statements":[{"statement":"match (a{UUID:130001}), (b{UUID:140001}) create (a)-[:PLACED]->(b)"}]}
//client to user
{"statements":[{"statement":"match (a{UUID:130001}), (b{UUID:90001}) create (a)-[:Has_User]->(b)"}]}


//user to metadata 
{"statements":[{"statement":"match (a{UUID:90001}), (b{UUID:110001}) create (a)-[:Authentication]->(b)"}]}

//user to metadata 
{"statements":[{"statement":"match (a{UUID:90001}), (b{UUID:100001}) create (a)-[:Has_Role]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:90001}), (b{UUID:100002}) create (a)-[:Has_Role]->(b)"}]}

//role to security tags
{"statements":[{"statement":"match (a{UUID:100001}), (b{UUID:120001}) create (a)-[:Financial]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100001}), (b{UUID:120002}) create (a)-[:Read]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100002}), (b{UUID:120002}) create (a)-[:Create_And_Update]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100002}), (b{UUID:120003}) create (a)-[:Financial]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100002}), (b{UUID:120004}) create (a)-[:Read]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100002}), (b{UUID:120005}) create (a)-[:Edit_Templates]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100002}), (b{UUID:120006}) create (a)-[:Edit_Blueprints]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100003}), (b{UUID:120007}) create (a)-[:Read]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100003}), (b{UUID:120007}) create (a)-[:Create_And_Update]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:100003}), (b{UUID:120007}) create (a)-[:Delete]->(b)"}]}

//order to serviceCatalogue
{"statements":[{"statement":"match (a{UUID:140001}), (b{UUID:60001}) create (a)-[:INCLUDING]->(b)"}]}

//serviceCatalogue to serviceCatalogueVersion
{"statements":[{"statement":"match (a{UUID:60001}), (b{UUID:70001}) create (a)-[:SC_version]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:60001}), (b{UUID:70002}) create (a)-[:SC_version]->(b)"}]}

//serviceCatalogue to scTaxonomy
{"statements":[{"statement":"match (a{UUID:60001}), (b{UUID:80001}) create (a)-[:INSTALLS]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:60001}), (b{UUID:80004}) create (a)-[:INSTALLS]->(b)"}]}

//scTaxonomy to scTaxonomy
{"statements":[{"statement":"match (a{UUID:80001}), (b{UUID:80002}) create (a)-[:sc_Taxonomy]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:80002}), (b{UUID:80003}) create (a)-[:sc_Taxonomy]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:80004}), (b{UUID:80003}) create (a)-[:sc_Taxonomy]->(b)"}]}

//scTaxonomy to blueprint
{"statements":[{"statement":"match (a{UUID:80004}), (b{UUID:40002}) create (a)-[:sc_Taxonomy_Blueprint]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:80001}), (b{UUID:40001}) create (a)-[:sc_Taxonomy_Blueprint]->(b)"}]}

//blueprint to task
{"statements":[{"statement":"match (a{UUID:40001}), (b{UUID:50001}) create (a)-[:Blueprint_Task]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:40001}), (b{UUID:50002}) create (a)-[:Blueprint_Task]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:40001}), (b{UUID:50003}) create (a)-[:Blueprint_Task]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:40001}), (b{UUID:50004}) create (a)-[:Blueprint_Task]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:40001}), (b{UUID:50005}) create (a)-[:Blueprint_Task]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:40001}), (b{UUID:50006}) create (a)-[:Blueprint_Task]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:40001}), (b{UUID:50007}) create (a)-[:Blueprint_Task]->(b)"}]}

//task to template function
{"statements":[{"statement":"match (a{UUID:50001}), (b{UUID:30004}) create (a)-[:Task_Function{value:'{\"URL\":\"https://cf.com/mysql\"}'}]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50001}), (b{UUID:30005}) create (a)-[:Task_Function{value:'{\"name\":\"mysql\",\"version\":\"2.3\",\"uuid\":\"5s77d88\"}'}]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50002}), (b{UUID:30005}) create (a)-[:Task_Function{value:'{\"workers\":\"1\",\"network\":\"cf1\",\"reuse_compilation_vms\":\"true\",\"cloud_properties\":[{\"instance_type\":\"m1.medium\",\"availability_zone\":\"us-west-2a\"}]}'}]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50003}), (b{UUID:30005}) create (a)-[:Task_Function{value:'{\"canaries\":\"1\",\"canary_watch_time\":\"30000-180000\",\"update_watch_time\":\"30000-180000\",\"max_in_flight\":\"4\"}'}]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50004}), (b{UUID:30005}) create (a)-[:Task_Function{value:'{\"- name\":\"default\",\"type\":\"dynamic\",\"cloud_properties\":[{\"security_group\":\"\",\"-default\":\"\"}]}'}]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50004}), (b{UUID:30005}) create (a)-[:Task_Function{value:'{\"- name\":\"elastic\",\"type\":\"vip\",\"cloud_properties\":\"{}\"}'}]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50005}), (b{UUID:30005}) create (a)-[:Task_Function{value:'{\"- name\":\"common\",\"network\":\"default\",\"size\":\"1\",\"stemcell\":[{\"name\":\"bosh-xen-ubuntu-trusty-go_agent\",\"version\":\"2976\"}],\"cloud_properties\":[{\"instance_type\":\"m1.medium\",\"availability_zone\":\"us-east-2a\",\"key_name\":\"Bosh_KeyPair\"}]}'}]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50005}), (b{UUID:30003}) create (a)-[:Task_Function]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:50007}), (b{UUID:30005}) create (a)-[:Task_Function{value:'{\"- name\":\"mysql\",\"template\":\"mysql\",\"instances\":\"1\",\"resource_pool\":\"common\",\"networks\":[{\"- name\":\"default\",\"default\":\"[dns, gateway]\"},{\"-name\":\"elastic\",\"static_ips\":\"-52.25.60.146\"}],\"persistent_disk\":\"10240\",\"properties\":[{\"admin_password\":\"root\",\"admin_username\":\"root\",\"max_connections\":\"1500\",\"port\":\"3306\",\"cluster_ips\":\"52.25.60.146\"}]}'}]->(b)"}]}
//cloud to IaaSConfig
{"statements":[{"statement":"match (a{UUID:50006}), (b{UUID:150001}) create (a)-[:Task_Function{value:'{\"BOSH v\":\"2.1\",\"director_uuid\":\"34h45-34hbjn-343jh-mnbj34\",\"target\":\"196.1.3.4\",\"UID\":\"admin\",\"PSW\":\"admin\",\"plugin\":\"aws\",\"proporties\":[{\"aws\":[{\"access_key_id\":\"AKGDFIUYGD\",\"secret_access_key\":\"LHGUGF\",\"region\":\"is-west-2\",\"default_key_name\":\"BOSH_KeyPair\",\"c2_private_key\":\"~./ssh/BOSH_KeyPair.pem\"}]}]}'}]->(b)"}]}

//Template Function to IaaSConfig
{"statements":[{"statement":"match (a{UUID:30001}), (b{UUID:150001}) create (a)-[:Config_Function]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:30002}), (b{UUID:150001}) create (a)-[:Config_Function]->(b)"}]}

//IaaSConfig to IaaSTemplate
{"statements":[{"statement":"match (a{UUID:150001}), (b{UUID:20001}) create (a)-[:IaaS_TemplateConfig]->(b)"}]}

//IaaSConfig to Taxonomy
{"statements":[{"statement":"match (a{UUID:150001}), (b{UUID:10001}) create (a)-[:Taxonomy_TemplateConfig]->(b)"}]}

//IaaSTemplate to Template Function
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:30001}) create (a)-[:IaaS_Function]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:30002}) create (a)-[:IaaS_Function]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:30003}) create (a)-[:IaaS_Function]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:30004}) create (a)-[:IaaS_Function]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:30005}) create (a)-[:IaaS_Function]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:30006}) create (a)-[:IaaS_Function]->(b)"}]}

//IaaSTemplate to Taxonomy
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:10001}) create (a)-[:IaaS_Taxonomy]->(b)"}]}
{"statements":[{"statement":"match (a{UUID:20001}), (b{UUID:10002}) create (a)-[:IaaS_Taxonomy]->(b)"}]}

//Client to IaaSConfig
{"statements":[{"statement":"match (a{UUID:130001}), (b{UUID:150001}) create (a)-[:ORDER_CONFIG]->(b)"}]}

//Order to IaaSConfig
{"statements":[{"statement":"match (a{UUID:140001}), (b{UUID:150001}) create (a)-[:ORDER_CONFIG]->(b)"}]}

//Order to Deployment
{"statements":[{"statement":"match (a{UUID:140001}), (b{UUID:160001}) create (a)-[:TO_DEPLOY]->(b)"}]}

//Deployment to ServiceCatalogue
{"statements":[{"statement":"match (a{UUID:160001}), (b{UUID:60001}) create (a)-[:DEPLOYING]->(b)"}]}

//Deployment to IaaSConfig
{"statements":[{"statement":"match (a{UUID:160001}), (b{UUID:150001}) create (a)-[:DEPLOY_ON]->(b)"}]}