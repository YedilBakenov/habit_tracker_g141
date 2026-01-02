INSERT INTO habbits(duration, habbit_name, meta, created_at, updated_at)
VALUES (20.3, 'smoking', 'about smoking', '2026-01-02', '2026-01-02'),
       (10.2, 'drinking', 'about drinking', '2026-01-02', '2026-01-02');

INSERT INTO cities(code, citi_name)
VALUES ('02', 'Almaty'),
       ('01', 'Astana');

INSERT INTO users(full_name, profession, city_id)
VALUES ('Serik Serikov', 'IT-specialist', 1),
       ('Berik Berikov', 'IT-specialist', 2);

INSERT INTO users_habbits(user_id, habbits_id)
VALUES (1, 1),
       (2, 2);