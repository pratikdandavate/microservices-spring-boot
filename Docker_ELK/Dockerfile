FROM sebp/elk

# overwrite existing file
RUN rm /etc/logstash/conf.d/logback.conf
COPY logback.conf /etc/logstash/conf.d/logback.conf