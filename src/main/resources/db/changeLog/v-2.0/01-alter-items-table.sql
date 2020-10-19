ALTER TABLE items ADD COLUMN user_id INTEGER
GO
ALTER TABLE items
      ADD CONSTRAINT user_id
          FOREIGN KEY (user_id)
              REFERENCES users(id)
GO