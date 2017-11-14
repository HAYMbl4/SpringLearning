CREATE TABLE IF NOT EXISTS springlearning.customer (
  id          BIGINT       NOT NULL DEFAULT nextval('springlearning.gen_object_id'),
  first_name  VARCHAR(256) NOT NULL,
  second_name VARCHAR(256),
  last_name   VARCHAR(256),
  phone       VARCHAR(64),
  email       VARCHAR(256),
  CONSTRAINT pk_customer PRIMARY KEY (id)
);