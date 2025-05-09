INSERT INTO usrs (id, username, phone) values
    ('dc1958ef-43c5-452c-b0fa-e079ee5a573a', 'JOHN', '79215551234'),
    ('04e5b548-ca96-4ea3-b46b-87a6d9f4d7e3','GARRY', '79037654321'),
    ('f04eaf73-ed1e-47ac-b47e-5a7f5373b822','JUSTIN', '79161234567'),
    ('8d92f228-eb85-43ad-b859-793ecb4eaef1','BOB', '79539876543'),
    ('c079a8f0-bf7e-47ff-a2dc-0b88bd26ef38','MARRY', '79987654321');

INSERT INTO subscriptions (id, subscription_name, start_date, usr_id) VALUES
    -- for JOHN
    ('ede49575-6b77-4d09-a564-461d421f1b9a', 'YOUTUBE_PREMIUM',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        'dc1958ef-43c5-452c-b0fa-e079ee5a573a'),
    ('536e7d0b-8200-472d-b4f7-b15a6f10434f', 'VK_MUSIC',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        'dc1958ef-43c5-452c-b0fa-e079ee5a573a'),
    ('bc0a0d33-cd25-4af6-b23a-1bd9db362ee9', 'YANDEX_PLUS',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        'dc1958ef-43c5-452c-b0fa-e079ee5a573a'),

    -- for GARRY
    ('910134b1-2227-4232-be26-b5ddb55d2f49', 'YOUTUBE_PREMIUM',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        '04e5b548-ca96-4ea3-b46b-87a6d9f4d7e3'),
    ('09a5f708-7c6b-48ba-9afb-60fcffbf3364', 'VK_MUSIC',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        '04e5b548-ca96-4ea3-b46b-87a6d9f4d7e3'),
    ('e5c0e9b0-c603-4516-939b-30972f1905f4', 'YANDEX_PLUS',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        '04e5b548-ca96-4ea3-b46b-87a6d9f4d7e3'),

    -- for JUSTIN
    ('3fd98d45-13eb-4615-a058-6c7449c16b07', 'YOUTUBE_PREMIUM',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        'f04eaf73-ed1e-47ac-b47e-5a7f5373b822'),

    -- for BOB
    ('3fe5d80e-6bbd-4510-9988-81086ece08b0', 'YOUTUBE_PREMIUM',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        '8d92f228-eb85-43ad-b859-793ecb4eaef1'),
    ('a5f21066-6e12-49a4-800d-74d36fee2b05', 'NETFLIX',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        '8d92f228-eb85-43ad-b859-793ecb4eaef1'),
    ('e2b05a01-d620-4924-8c48-95fc1be6167b', 'YANDEX_PLUS',
        TIMESTAMP '2023-01-01' + (random() * (now() - TIMESTAMP '2023-01-01')),
        '8d92f228-eb85-43ad-b859-793ecb4eaef1');