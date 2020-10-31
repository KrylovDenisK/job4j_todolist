CREATE TABLE category (
   id           SERIAL PRIMARY KEY ,
   name         VARCHAR(255) NOT NULL
)
GO
INSERT INTO category(name)
            VALUES ('Service'), ('Technical support'), ('Incident')
GO
CREATE TABLE items_category (
      item_id integer references items(id),
	    categories_id integer references category(id),
	    primary key(item_id, categories_id)
)
GO