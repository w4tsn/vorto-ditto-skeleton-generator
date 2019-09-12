Eclipse Ditto Skeleton Generator
===

This project is an [Eclipse Ditto](https://www.eclipse.org/ditto/) Skeleton generator to export Vorto models directly to json-representation usable as Things with sane defaults.

How to use
---

First build the java runner which will execute the generators in this project as spring micro-services with:

```bash
mvn package
```

Adding more generators involes

* creating a new generator project like `generator-eclipsedittoskeleton`
* adding the new generator to the `./pom.xml` file in the section modules
* adding the new generator to the `generator-runner` project

Build the docker image with:

```bash
cd generator-runner
docker-compose build
```

Test/run with:

```bash
cd generator-runner
docker-compose up # or docker-compose up -d for deamonized mode
```

Configuration for docker-compose
---

Create the file `./vorto-variables.env` in the projects root.

```env
# Generell settings
GENERATOR_USER=vorto_generators
GENERATOR_PASSWORD=1234
VORTO_PORT=8080
CONTEXT_PATH=/

# Repository
DATASOURCE=mysql
AUTH_PROVIDER=github
ADMIN_USER=<your-github-user>
GITHUB_CLIENT_ID=<your-github-client-id>
GITHUB_CLIENT_SECRET=<your-github-client-secret>

# Generators
VORTO_URL=http://repository:8080
3RD_PARTY_SERVICE_URL=http://generators:8080

# database
MYSQL_USER=root
MYSQL_ROOT_PASSWORD=<your-mysql-password>
MYSQL_DATABASE=vorto
```

Resources and Sources
---

The following open source projects / sources were used for this project.

* [BoshIoT Provisioning Scripts Ditto-JSON Template](https://github.com/eclipse/vorto/blob/development/generators/generator-boschiotsuite/boschiotsuite-cloud/src/main/java/org/eclipse/vorto/codegen/bosch/templates/ProvisionDeviceScriptTemplate.xtend)
* [API v1 Generators in eclipse/vorto-examples repository](https://github.com/eclipse/vorto-examples/tree/master/vorto-generators)
* [Docker configuration and files from eclipse/vorto/docker](https://github.com/eclipse/vorto/tree/master/docker)

Next up
---

Bringing this project to Vorto API Version 2.
