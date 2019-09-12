FROM java:8
# Add folder to hold jar file
RUN mkdir /code
WORKDIR /code
ARG JAR_FILE
VOLUME /root/.vorto
RUN echo "deb [check-valid-until=no] http://archive.debian.org/debian jessie-backports main" > /etc/apt/sources.list.d/jessie-backports.list
RUN sed -i '/jessie-updates/d' /etc/apt/sources.list # Now archived
RUN apt-get -o Acquire::Check-Valid-Until=false update
RUN apt-get install -y python3 python3-yaml wget
ADD ./${JAR_FILE} /code/generators.jar
ADD ./docker/scripts/wait-for-it.sh /code
RUN chmod +x wait-for-it.sh
ADD ./docker/scripts/run.py /code
CMD ["/bin/bash", "/code/wait-for-it.sh", "-t", "40", "repository:8080",  "--", "/usr/bin/python3", "run.py", "--thirdpartygenerator"]
#Wait for repository to become avaliable
