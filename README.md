## technical-test for Educamas


**Technologies**

> gradle version 8.5
>
> Spring boot 3.4.5
>
> jdk 17
>
> postgrest 13.16


## Prerequisites
You need to have it installed

> git
>
> gradle 8.5
>
> jdk 17
>
> postgrest 14.18

## Commands to use

### Build Project
```shell
gradle clean build
```

### Run

**Create container for db (It is done from command to not create docker.build)**
```shell
docker pull postgres 14.18 && sudo  docker run -d --name db_educamas -e POSTGRES_PASSWORD="write password" -e POSTGRES_USER="write user" -p 5432:5432 -e POSTGRES_DB=db_educamas postgres:14.18
```

```shell
gradle bootRun
```
##Evidences
![Image](https://github.com/user-attachments/assets/88ae002c-6cf4-4da9-a5ae-78b8a87ed820)

* Collection https://github.com/juanpablommm/technical-test/issues/2#issue-3058525070

© Juan Pablo Montoya Ospinal
