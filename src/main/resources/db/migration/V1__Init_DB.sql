CREATE TYPE subscription_name AS ENUM ('YOUTUBE_PREMIUM', 'VK_MUSIC', 'YANDEX_PLUS', 'NETFLIX');

CREATE TABLE if NOT EXISTS usrs (
     id uuid PRIMARY KEY,
     username   varchar(60),
     phone  varchar(11) UNIQUE
);

CREATE TABLE if NOT EXISTS subscriptions (
     id uuid PRIMARY KEY,
     subscription_name subscription_name,
     start_date TIMESTAMP,
     usr_id uuid,
     CONSTRAINT "fk_usr_id" FOREIGN KEY (usr_id) REFERENCES usrs(id)
);

