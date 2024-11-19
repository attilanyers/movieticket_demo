-- Movie
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (1, 'title-483', 'director-50', 'genre-349', 384, 230, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (2, 'title-280', 'director-65', 'genre-215', 648, 481, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (3, 'title-446', 'director-686', 'genre-955', 684, 69, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (4, 'title-88', 'director-193', 'genre-972', 776, 399, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (5, 'title-103', 'director-525', 'genre-369', 391, 650, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (6, 'title-431', 'director-15', 'genre-626', 934, 811, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (7, 'title-458', 'director-16', 'genre-758', 526, 761, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (8, 'title-811', 'director-547', 'genre-116', 229, 19, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (9, 'title-459', 'director-47', 'genre-356', 192, 874, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (10, 'title-661', 'director-452', 'genre-661', 188, 92, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (11, 'title-885', 'director-187', 'genre-327', 971, 602, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (12, 'title-525', 'director-871', 'genre-235', 978, 450, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (13, 'title-904', 'director-501', 'genre-293', 634, 170, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (14, 'title-564', 'director-210', 'genre-70', 164, 372, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (15, 'title-282', 'director-441', 'genre-780', 884, 978, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (16, 'title-792', 'director-541', 'genre-85', 185, 143, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (17, 'title-654', 'director-178', 'genre-507', 860, 254, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (18, 'title-621', 'director-404', 'genre-523', 912, 522, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (19, 'title-466', 'director-311', 'genre-89', 704, 868, NOW());
INSERT INTO movie (id, title, director, genre, duration, length, created_at) VALUES (20, 'title-53', 'director-255', 'genre-546', 679, 144, NOW());

-- Cinema
INSERT INTO cinema (id, name, location, created_at) VALUES (1, 'name-768', 'location-153', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (2, 'name-18', 'location-78', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (3, 'name-570', 'location-759', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (4, 'name-586', 'location-46', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (5, 'name-881', 'location-715', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (6, 'name-351', 'location-620', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (7, 'name-742', 'location-123', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (8, 'name-637', 'location-614', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (9, 'name-268', 'location-543', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (10, 'name-732', 'location-158', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (11, 'name-921', 'location-209', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (12, 'name-702', 'location-527', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (13, 'name-191', 'location-465', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (14, 'name-565', 'location-899', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (15, 'name-390', 'location-327', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (16, 'name-811', 'location-603', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (17, 'name-886', 'location-186', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (18, 'name-557', 'location-952', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (19, 'name-861', 'location-451', NOW());
INSERT INTO cinema (id, name, location, created_at) VALUES (20, 'name-164', 'location-176', NOW());

-- User
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('ba146a8d-4b61-4fc2-849b-59dcdcf07a99'::uuid, 'admin-1@email.com', '$2a$10$9STtl2T.YiJ7LXWSaylpLuewFz1kwg/sY1S5NI2VzISZ/AGv.iUUC', 'passwordResetToken-621', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('edf879e3-4157-46bd-a621-9835026cf3d3'::uuid, 'admin-2@email.com', '$2a$10$.hWsY4oWBgxW7CTPxDvFVOtU9fBjd.GZwNfpEmmGYtIWyC/9cEh1q', 'passwordResetToken-819', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('b47a0826-3372-4366-91c7-e25491cc7789'::uuid, 'admin-3@email.com', '$2a$10$GqpOoibQNXBIvD31zCWZpOUVrG9RAKnHHQGC0JaULd37WOYA0JdUm', 'passwordResetToken-126', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('e24fda27-829f-492d-a914-6a96b56c75cf'::uuid, 'user-1@email.com', '$2a$10$9fLq5/e.Pq0v4K5eV85q8O.VDaNmbw.SViUrkZBs0ATiBGEZXheoC', 'passwordResetToken-501', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('e84bab9e-d5f3-4b3e-a935-8a8e2bd21671'::uuid, 'admin-4@email.com', '$2a$10$/.uNJBSv1JEwkhbC5NZLceTYnahxKhHIuWOZ.wKMrnM3HywCNCyU.', 'passwordResetToken-921', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('7ffd7f37-ea0e-415d-a978-589526f177cc'::uuid, 'admin-5@email.com', '$2a$10$dOHeftz1cbcfE53663/pLuMGpoRvX5DWx8aVAS3w3S8Dr/HtfftpW', 'passwordResetToken-833', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('a08285d9-c545-48ab-83b6-73b24bf6c1ff'::uuid, 'user-2@email.com', '$2a$10$MUw5bF4p2X/EdYsvev9Zme5kaBVA3322tbxs986rWUf5UKapZwNJy', 'passwordResetToken-300', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('65bc065b-b78e-43d4-988c-aab333acfd1d'::uuid, 'user-3@email.com', '$2a$10$iXvb/vtVo1sGHoYoqLxnaecvJLBG9Cd44ODzY7PcjoZoUtaWLEBXq', 'passwordResetToken-30', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('679b8ff4-791f-4b3b-81d5-f3e4235029d1'::uuid, 'user-4@email.com', '$2a$10$2mHh5cWmi5yp0M7kKTnqveJSZNT7fjxevbSTrq0yzVJwdOSDlf.YG', 'passwordResetToken-411', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('5c2977ec-e313-4c41-97e2-df032430e2eb'::uuid, 'admin-6@email.com', '$2a$10$hqg6dpfz8CzBPbh6g9mK2uRyIH1D0nNZIIy.AJj/HHCypJQnrzNY2', 'passwordResetToken-116', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('9254461e-77a7-4408-bf2f-0ece9470a87f'::uuid, 'user-5@email.com', '$2a$10$HFrLArHoYc2Z1D0UnDvy.O7HpZ5Mf89ZVNyosmPhIPv7nmrJr829C', 'passwordResetToken-899', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('378ff15e-df94-47c5-912f-79906d19e962'::uuid, 'user-6@email.com', '$2a$10$GCbohJIsVSY23JVKpgk54eqYFFNr9SL6rmCsl4d3kV0MZ1/Kr9GH6', 'passwordResetToken-562', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('eedf32dd-1bd9-40cb-8169-f5d7b5896d10'::uuid, 'user-7@email.com', '$2a$10$Sg6xZmeOYldXLgwlOno4GegbW5IpyEOCc.MIfHSmeurs9/zICwbva', 'passwordResetToken-769', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('5dd1bfcd-03bf-41cd-84af-77a7baaa6d7d'::uuid, 'admin-7@email.com', '$2a$10$z4U8hMaZlYiQ/QslRRT3ROcLrJrrlCwQ57PqiMBWlSt1p8T/Woila', 'passwordResetToken-118', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('50acc7eb-c0f5-431f-bc3a-680b1324c2ef'::uuid, 'admin-8@email.com', '$2a$10$PpOoQTr06yXD4XUpw8put.EXPChtU2/lGwruhKyralTtDHCyZBuCq', 'passwordResetToken-822', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('eff3cfe1-c844-46a3-9c4d-1aff8fb47ab7'::uuid, 'admin-9@email.com', '$2a$10$iRwlk3dbgsXcaJNCtVuKnuTcRHTuxPL5nMUJInpLZnBiAUs8B6QV6', 'passwordResetToken-723', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('0ad82090-ef97-48dc-937e-e88029af8bfe'::uuid, 'user-8@email.com', '$2a$10$kendSW1YDeqvtQzv3zXEC./FneI2Lw3XIall0Aq..NL9vrqjYrCSi', 'passwordResetToken-804', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('80b2f318-8a56-4c51-b1be-a6a5a00fd5fc'::uuid, 'user-9@email.com', '$2a$10$d5IQKDEciup.XVMhBQL.bOLLN9WJln6jgdjTRD8kgIsfZ3Wihqjum', 'passwordResetToken-68', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('fb0581e3-0556-48b3-be79-b62fada31ff8'::uuid, 'user-10@email.com', '$2a$10$eLCps1VFmnxNP15wBGCJN.qSJbwtuA2Txk804GkOC0ogs7iXvEufa', 'passwordResetToken-851', NOW());
INSERT INTO "user"
 (id, email, password, password_reset_token, created_at) VALUES ('e1075af3-1e47-4730-8001-70860b36cdbf'::uuid, 'user-11@email.com', '$2a$10$wo/edn3yDuS0zYtLY5/Rq.OM45XdcvedlHXppMHn5k9WlAT/puGny', 'passwordResetToken-916', NOW());

-- Screening
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (1, 5, 3, '2024-11-23 11:42', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (2, 5, 10, '2024-11-15 19:35', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (3, 7, 19, '2024-11-16 01:57', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (4, 18, 7, '2024-11-23 17:02', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (5, 17, 10, '2024-11-20 12:38', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (6, 8, 19, '2024-11-17 06:30', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (7, 15, 9, '2024-11-14 05:40', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (8, 17, 1, '2024-11-23 18:13', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (9, 3, 7, '2024-11-24 22:19', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (10, 7, 13, '2024-11-22 04:34', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (11, 4, 1, '2024-11-22 10:02', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (12, 18, 11, '2024-11-13 07:49', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (13, 12, 7, '2024-11-22 17:31', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (14, 9, 11, '2024-11-18 02:01', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (15, 7, 11, '2024-11-23 20:52', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (16, 17, 5, '2024-11-14 01:13', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (17, 8, 15, '2024-11-21 14:17', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (18, 10, 5, '2024-11-23 19:35', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (19, 14, 14, '2024-11-12 04:09', NOW());
INSERT INTO screening (id, cinema_id, movie_id, start_time, created_at) VALUES (20, 19, 3, '2024-11-16 23:29', NOW());

-- Reservation
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (1, 16, 'b47a0826-3372-4366-91c7-e25491cc7789'::uuid, 'seatNumber-701', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (2, 16, 'eff3cfe1-c844-46a3-9c4d-1aff8fb47ab7'::uuid, 'seatNumber-425', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (3, 7, '65bc065b-b78e-43d4-988c-aab333acfd1d'::uuid, 'seatNumber-745', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (4, 11, '65bc065b-b78e-43d4-988c-aab333acfd1d'::uuid, 'seatNumber-644', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (5, 3, 'e24fda27-829f-492d-a914-6a96b56c75cf'::uuid, 'seatNumber-524', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (6, 3, 'b47a0826-3372-4366-91c7-e25491cc7789'::uuid, 'seatNumber-290', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (7, 13, 'fb0581e3-0556-48b3-be79-b62fada31ff8'::uuid, 'seatNumber-265', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (8, 15, 'e1075af3-1e47-4730-8001-70860b36cdbf'::uuid, 'seatNumber-108', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (9, 12, '9254461e-77a7-4408-bf2f-0ece9470a87f'::uuid, 'seatNumber-368', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (10, 19, 'ba146a8d-4b61-4fc2-849b-59dcdcf07a99'::uuid, 'seatNumber-545', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (11, 20, '65bc065b-b78e-43d4-988c-aab333acfd1d'::uuid, 'seatNumber-107', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (12, 5, '80b2f318-8a56-4c51-b1be-a6a5a00fd5fc'::uuid, 'seatNumber-940', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (13, 10, '5c2977ec-e313-4c41-97e2-df032430e2eb'::uuid, 'seatNumber-619', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (14, 9, 'e1075af3-1e47-4730-8001-70860b36cdbf'::uuid, 'seatNumber-36', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (15, 5, 'eedf32dd-1bd9-40cb-8169-f5d7b5896d10'::uuid, 'seatNumber-284', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (16, 14, 'fb0581e3-0556-48b3-be79-b62fada31ff8'::uuid, 'seatNumber-361', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (17, 3, '679b8ff4-791f-4b3b-81d5-f3e4235029d1'::uuid, 'seatNumber-263', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (18, 3, 'edf879e3-4157-46bd-a621-9835026cf3d3'::uuid, 'seatNumber-876', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (19, 12, 'edf879e3-4157-46bd-a621-9835026cf3d3'::uuid, 'seatNumber-168', NOW());
INSERT INTO reservation (id, screening_id, user_id, seat_number, created_at) VALUES (20, 13, '679b8ff4-791f-4b3b-81d5-f3e4235029d1'::uuid, 'seatNumber-144', NOW());

-- UserAuthority
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('bef54b53-9697-406d-98b5-933797183b6d'::uuid, 'ba146a8d-4b61-4fc2-849b-59dcdcf07a99'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('ef2471db-f161-4977-8c2b-491cd1f15ab5'::uuid, 'edf879e3-4157-46bd-a621-9835026cf3d3'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('7f57c467-c88a-4b20-9e7f-e0c7d0f185a1'::uuid, 'b47a0826-3372-4366-91c7-e25491cc7789'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('b4d8d902-dd8b-4256-bdf4-21885aa9362f'::uuid, 'e24fda27-829f-492d-a914-6a96b56c75cf'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('0c0ce59f-fdd2-42a8-ace4-052444b28935'::uuid, 'e84bab9e-d5f3-4b3e-a935-8a8e2bd21671'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('92e6f588-f0bc-4b07-9ec2-084b91804e82'::uuid, '7ffd7f37-ea0e-415d-a978-589526f177cc'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('63561ffb-11ee-4911-81f0-df261b22c386'::uuid, 'a08285d9-c545-48ab-83b6-73b24bf6c1ff'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('d3589733-dc60-4978-9407-bcc9ee7b9588'::uuid, '65bc065b-b78e-43d4-988c-aab333acfd1d'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('12661d08-c292-48f8-8108-7e6f67c8dcff'::uuid, '679b8ff4-791f-4b3b-81d5-f3e4235029d1'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('d28559ca-146e-452d-b3ec-a870904e6596'::uuid, '5c2977ec-e313-4c41-97e2-df032430e2eb'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('630fe759-a638-49ba-ac5a-46a76ae15e36'::uuid, '9254461e-77a7-4408-bf2f-0ece9470a87f'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('b8644d9d-cd4c-49e2-ae93-e57a0a5c5c20'::uuid, '378ff15e-df94-47c5-912f-79906d19e962'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('8377112e-ec6e-4f51-8cb3-2ae40c7e11a4'::uuid, 'eedf32dd-1bd9-40cb-8169-f5d7b5896d10'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('770f198d-e115-4bca-8d28-671c6b56ddc3'::uuid, '5dd1bfcd-03bf-41cd-84af-77a7baaa6d7d'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('4f3e38c9-2d6b-41c6-a25a-9175fc4bebb4'::uuid, '50acc7eb-c0f5-431f-bc3a-680b1324c2ef'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('d2fae8a7-d4ca-4e01-8465-818e48349507'::uuid, 'eff3cfe1-c844-46a3-9c4d-1aff8fb47ab7'::uuid, 'ADMIN', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('c8de157b-50bd-4bd3-9a84-e86afa7702e8'::uuid, '0ad82090-ef97-48dc-937e-e88029af8bfe'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('70c957e5-28b2-4c40-b037-2ff1a6ebb7c6'::uuid, '80b2f318-8a56-4c51-b1be-a6a5a00fd5fc'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('f5a52738-4a84-4660-94f2-e91b824ba17e'::uuid, 'fb0581e3-0556-48b3-be79-b62fada31ff8'::uuid, 'USER', NOW());
INSERT INTO user_authority (id, user_id, authority, created_at) VALUES ('3c13a4ab-4618-48a4-afe8-b4b9909ebb6c'::uuid, 'e1075af3-1e47-4730-8001-70860b36cdbf'::uuid, 'USER', NOW());

