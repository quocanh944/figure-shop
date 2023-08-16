-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2023 at 05:15 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `figure-shop`
--

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `name`) VALUES
(1, 'DXF'),
(2, 'SEGA'),
(3, 'Q Posket'),
(4, 'Ichiban Kuji'),
(5, 'Banpresto Grandista');

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`id`, `name`) VALUES
(1, 'One Piece'),
(2, 'Kimetsu no Yaiba'),
(3, 'Jujutsu Kaisen'),
(4, 'Dragon Ball'),
(5, 'Chainsaw Man'),
(6, 'Bleach'),
(7, 'Spy x Family'),
(8, 'My Hero Academia');

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `quantity`, `brand_id`, `film_id`) VALUES
(1, 'Hàng chính hãng Nhật Bản\nNhân vật: Law\nAnime: One piece\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbtq7n30', 'Mô hình nhân vật Law phim One Piece dòng Banpresto DXF The Grandline Men Wanokuni vol.14', 400000, 1, NULL, 1),
(2, 'Hàng chính hãng Nhật Bản\nNhân vật: Marco\nAnime: One piece\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbsbhu58', 'Mô hình nhân vật Marco phim One Piece dòng Banpresto DXF The Grandline Men Wanokuni vol.18', 400000, 1, NULL, 1),
(3, 'Hàng chính hãng Nhật Bản\nNhân vật: Kid\nAnime: One piece\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbzchf1b', 'Mô hình nhân vật Kid phim One Piece dòng Banpresto DXF The Grandline Men Wanokuni vol.15', 400000, 1, NULL, 1),
(4, 'Hàng chính hãng Nhật Bản\nNhân vật: Robin\nAnime: One piece\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc3k1eab', 'Mô hình nhân vật Robin phim One Piece dòng Banpresto DXF The grandline Lady Wanokuni vol.6', 400000, 1, NULL, 1),
(5, 'Hàng chính hãng Nhật Bản\nNhân vật: Mahito\nAnime: Jujustu Kaisen -Chú thuật hồi chiến\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh0cix53wstv2d', 'Mô hình nhân vật Mahito phim Jujutsu Kaisen dòng Banpresto DXF', 350000, 1, 1, 3),
(6, 'Hàng chính hãng Nhật Bản\nNhân vật: Satorou Gojo\nAnime: Jujustu Kaisen -Chú thuật hồi chiến\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh0fa5dq3iuq13', 'Mô hình nhân vật Satorou Gojo phim Jujutsu Kaisen dòng Banpresto DXF', 350000, 1, 1, 3),
(7, 'Hàng chính hãng Nhật Bản\nNhân vật: Yuji Itadori\nAnime: Jujustu Kaisen -Chú thuật hồi chiến\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh0cix53zlyrce', 'Mô hình nhân vật Yuji Itadori phim Jujutsu Kaisen dòng Banpresto DXF', 350000, 1, 1, 3),
(8, 'Hàng chính hãng Nhật Bản\nNhân vật: Megumi Fushiguro\nAnime: Jujustu Kaisen -Chú thuật hồi chiến\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh0cix5410j719', 'Mô hình nhân vật Megumi Fushiguro phim Jujutsu Kaisen dòng Banpresto DXF', 350000, 1, 1, 3),
(9, 'Hàng chính hãng Nhật Bản\nNhân vật: Sukuna\nAnime: Jujustu Kaisen -Chú thuật hồi chiến\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh0cix53y7eb3f', 'Mô hình nhân vật Sukuna phim Jujutsu Kaisen dòng Banpresto DXF', 350000, 0, 1, 3),
(10, 'Hàng chính hãng Nhật Bản\nNhân vật: Kocho Shinobu\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgyy617vsqnnb1', 'Mô hình nhân vật Kocho Shinobu phim Kimetsu no Yaiba dòng Banpresto Q Posket', 250000, 4, 3, 2),
(11, 'Hàng chính hãng Nhật Bản\nNhân vật: Akaza\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgyy617vvjsjd4', 'Mô hình nhân vật Akaza phim Kimetsu no Yaiba dòng Banpresto Q posket version A', 500000, 0, 3, 2),
(12, 'Hàng chính hãng Nhật Bản\nNhân vật: Obanai Iguro\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgyy617vu583e7', 'Mô hình nhân vật Obanai Iguro phim Kimetsu no Yaiba dòng Banpresto Q posket', 500000, 0, 3, 2),
(13, 'Hàng chính hãng Nhật Bản\nNhân vật: Kanao Tsuyuri\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgz3boh3e0hfd3', 'Mô hình nhân vật Kanao Tsuyuri phim Kimetsu no Yaiba dòng Banpresto Q posket', 500000, 0, 3, 2),
(14, 'Hàng chính hãng Nhật Bản\nNhân vật: Inosuke Hashibira\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/d32cfcf3ff89e878dce8c967802cb32a', 'Mô hình nhân vật Inosuke Hashibira phim Kimetsu no Yaiba dòng Banpresto Grandista', 400000, 2, 5, 2),
(15, 'Hàng chính hãng Nhật Bản\nNhân vật: Tankjiro Kamado\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/6bae809a4ab997892e844c72657473b6', 'Mô hình nhân vật Tankjiro Kamado phim Kimetsu no Yaiba dòng Banpresto Grandista', 400000, 2, 5, 2),
(16, 'Hàng chính hãng Nhật Bản\nNhân vật: Shinobu Kocho\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe4y38z99', 'Mô hình nhân vật Trùng trụ Shinobu Kocho phim Kimetsu no Yaiba dòng Sega Chuugoukaigi', 375000, 2, 2, 2),
(17, 'Hàng chính hãng Nhật Bản\nNhân vật: Uzui Tengen\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe57x2qe2', 'Mô hình nhân vật Âm trụ Uzui Tengen phim Kimetsu no Yaiba dòng Sega Premium Chokonose', 375000, 2, 2, 2),
(18, 'Hàng chính hãng Nhật Bản\nNhân vật: Uzui Tengen\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe5exwy21', 'Mô hình nhân vật Âm trụ Uzui Tengen phim Kimetsu no Yaiba dòng Sega Shinobushouzoku', 375000, 2, 2, 2),
(19, 'Nhân vật : Zenitsu\nTình trạng: new\nHãng sản xuất : bandai\n👉 Mọi thắc mắc vui lòng liên hệ Facebook : http://facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe5exwy21', 'Mô hình nhân vật Zenitsu phim Kimetsu no Yaiba dòng Banpresto Ichiban Kuji', 850000, 1, 4, 2),
(20, 'Hàng chính hãng Nhật Bản \nNhân vật: Daki \nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgz3boh3ff1v68', 'Mô hình nhân vật Thượng huyền lục Daki phim Kimetsu no Yaiba Daki dòng Banpresto DXF', 400000, 1, 1, 2),
(21, 'Hàng chính hãng Nhật Bản \nNhân vật: Obanai Iguro\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgz4ej7ml2b75d', 'Mô hình nhân vật xà trụ Obanai Iguro phim Kimetsu no Yaiba dòng Banpresto DXF', 400000, 1, 1, 2),
(22, 'Hàng chính hãng Nhật Bản \nNhân vật: Inosuke Hashibira\nAnime: Kimetsu no Yaiba\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgz4ej7mi96bcd', 'Mô hình nhân vật đấng ỉn Inosuke Hashibira phim Kimetsu no Yaiba dòng Banpresto DXF', 400000, 1, 1, 2),
(23, 'Hàng chính hãng Nhật Bản \nNhân vật: Makima\nAnime: Chainsaw man\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc96gj8f', 'Mô hình nhân vật Makima phim Chainsaw Man dòng Furyu Noodle Stopper', 450000, 1, NULL, 5),
(24, 'Hàng chính hãng Nhật Bản \nNhân vật: Denji\nAnime: Chainsaw man\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3ncu8z771', 'Mô hình nhân vật phim Denji Chainsaw Man dòng Furyu Noodle Stopper', 450000, 1, NULL, 5),
(25, 'Hàng chính hãng Nhật Bản \nNhân vật: Aki Hayakawa\nAnime: Chainsaw man\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh0b36xss9v7b3', 'Mô hình nhân vật Aki Hayakawa phim Chainsaw Man dòng Banpresto Chain Spirits Vol.2', 350000, 1, NULL, 5),
(26, 'Hàng chính hãng Nhật Bản \nNhân vật: Power \nAnime: Chainsaw man\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3ncygojcb', 'Mô hình nhân vật Power phim Chainsaw Man dòng Taito', 550000, 2, NULL, 5),
(27, 'Hàng chính hãng Nhật Bản \nNhân vật: Black \nAnime: Dragon ball\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc25gya2', 'Mô hình nhân vật Black phim Dragon ball Super dòng Banpresto Solid Edge Works', 350000, 1, NULL, 4),
(28, 'Hàng chính hãng Nhật Bản \nNhân vật: Vegeta Super saiyan Blue\nAnime: Dragon ball\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc7rw347', 'Mô hình nhân vật Vegeta Super saiyan Blue phim Dragon ball dòng Maximatic Banpresto', 450000, 1, NULL, 4),
(29, 'Hàng chính hãng Nhật Bản \nNhân vật: Son Gotenks\nAnime: Dragon ball\nMọi thông tin chi tiết xin liên hệ f.b: facebook.com/sakefigure', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbwj76e4', 'Mô hình nhân vật Son Gotenks phim Dragon ball Z dòng Banpresto Solid Edge Works', 350000, 1, NULL, 4);

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`id`, `type`, `url`, `product_id`) VALUES
(1, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbv4s3bb', 1),
(2, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbwjcjc2', 1),
(3, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbxxwza2', 1),
(5, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbv4mq31', 2),
(6, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nbxxrmc9', 2),
(7, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc0qwif4', 2),
(8, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc0r1va3', 3),
(9, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc25mb8f', 3),
(10, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc3k6r02', 3),
(11, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc6d6ae8', 4),
(12, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lgzyg3nc96b6e2', 4),
(13, 'IMAGE', 'https://cf.shopee.vn/file/52b99a5c41827c24bbec1c743c62562a', 14),
(14, 'IMAGE', 'https://cf.shopee.vn/file/ba03b59b1989903aefd42107f6b8e2ee', 14),
(15, 'IMAGE', 'https://cf.shopee.vn/file/14792b84d29361c883cb39d18645d787', 14),
(16, 'IMAGE', 'https://cf.shopee.vn/file/9b8c9e4c02f0eaa2c366fff9c18c9712', 15),
(17, 'IMAGE', 'https://cf.shopee.vn/file/e2b3a78a44b5d59e4f67d612afd5290b', 15),
(18, 'IMAGE', 'https://cf.shopee.vn/file/229d8b660dfaffb52e5c7dd780ec262f', 15),
(19, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe4y33m99', 16),
(20, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe4in0328', 16),
(21, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe5szle0c', 16),
(22, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe4imuq6d', 16),
(23, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe5djci86', 16),
(24, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe436lu86', 16),
(25, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe436r7ad', 16),
(26, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe68fua92', 16),
(27, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe59bn6be', 17),
(28, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe5aq7m64', 17),
(29, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe5c4s2a7', 17),
(30, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe4h8fnb7', 18),
(31, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe4ftv7ac', 18),
(32, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe50w8id1', 18),
(33, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe52asy57', 18),
(34, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe53pde79', 18),
(35, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe553xuf4', 18),
(36, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe56iiadb', 18),
(37, 'IMAGE', 'https://cf.shopee.vn/file/vn-11134207-7qukw-lh4xnoe4h8fnb7', 19);

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

--
-- Dumping data for table `user_entity`
--

INSERT INTO `user_entity` (`id`, `avatar`, `created_date`, `email`, `full_name`, `password`, `phone_number`, `updated_date`) VALUES
(2, NULL, '2023-08-14 06:52:12.000000', 'quocanh944@gmail.com', NULL, '$2a$10$3hDP7nkWnSGZqOdcxhdIRe7aj39IKO8GFayZq2pjTMulIDL8gzC5i', NULL, NULL),
(5, NULL, '2023-08-16 12:53:15.000000', 'quoc@gmail.com', NULL, '$2a$10$J1y7UWcC9aiAMblT.TP3xeTdGwDqcnkIIO4yVMB1N1qNHZxUDNEPy', NULL, NULL);

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(2, 1),
(5, 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
