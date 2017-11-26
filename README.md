# cloudstorage
A website for cloud file management
## Use Case
![use case](usecase.png)
## Deployment
### step 1 Clone the repository
`git clone git@github.com:songhm3/cloudstorage.git cloudstorage`

`cd cloudstorage`

### step 2 Generate War package 
`mvn packaging`

### step2 Deploy War package to Tomcat Server 
`sudo cp ~/cloudstorage.war /path/to/your/tomcat/webapps/`

Now you can verify the server on your browser: http://localhost:8080/cloudstorage/

## Technology
1. Spring as IOC Container
2. SpringMVC 
3. Mybatis
4. MySQL
