Jenkins
    1. docker container run -d --name jenkins-master -p 8080:8080 -p 50000:50000 -v ~/jenkins_home:/var/jenkins_home jenkins/jenkins:lts
    2. on first activation: docker container logs -f jenkins-master
        Please use the following password to proceed to installation:
        xxxx

    3. Some useful info:
       Jenkins stores its data in the /var/jenkins_home
       Job:
        A job is a user-configured description of work which Jenkins should perform
        to see jobs description: docker exec jenkins-master cat /var/jenkins_home/jobs/job_name/config.xml
       Build:
        A build is the result of a single execution of a job
        to see builds description: docker exec jenkins-master ls -lh /var/jenkins_home/jobs/job_name/builds
            output: jenkins jenkins 170 Oct  6 17:19 1
                    jenkins jenkins   2 Oct  6 17:19 lastFailedBuild -> -1
                    jenkins jenkins   1 Oct  6 17:19 lastStableBuild -> 1
                    jenkins jenkins   1 Oct  6 17:19 lastSuccessfulBuild -> 1
                    jenkins jenkins   2 Oct  6 17:19 lastUnstableBuild -> -1
                    jenkins jenkins   2 Oct  6 17:19 lastUnsuccessfulBuild -> -1
        Artifact:
            An artifact is an immutable file (such as the .jar created by the Maven package phase) generated during a
            build which can be optionally archived into the Jenkins master for later retrieval by users.
        Workspace:
            Jenkins allocates a unique “workspace directory” for each job. This is the directory where the code is checked out and builds happen.
            docker exec jenkins-master ls -lha /var/jenkins_home/workspace/job_name
        Executor
            An executor is a thread that executes builds. The number of executors controls the number of concurrent builds that
            Jenkins can perform on a node. A node may have zero or more executors configured. Sometimes, having zero executors
            on a node makes sense, such as in the case you want to relieve the master node from running jobs by entirely delegating it to slave nodes.