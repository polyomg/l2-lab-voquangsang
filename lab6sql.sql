CREATE DATABASE WebShop;
GO
USE WebShop;
GO

CREATE TABLE Categories (
    Id NVARCHAR(10) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL
);

-- DỮ LIỆU MẪU
INSERT INTO Categories (Id, Name) VALUES
('C01', N'Điện thoại'),
('C02', N'Laptop'),
('C03', N'Thiết bị âm thanh'),
('C04', N'Phụ kiện'),
('C05', N'Đồng hồ thông minh');

CREATE TABLE Products (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Image NVARCHAR(255),
    Price FLOAT,
    CreateDate DATE DEFAULT GETDATE(),
    Available BIT,
    CategoryId NVARCHAR(10) FOREIGN KEY REFERENCES Categories(Id)
);

INSERT INTO Products (Name, Image, Price, Available, CategoryId) VALUES
(N'iPhone 14 Pro', 'iphone14.jpg', 29990000, 1, 'C01'),
(N'Samsung Galaxy S23', 's23.jpg', 23990000, 1, 'C01'),
(N'MacBook Air M2', 'macbookm2.jpg', 32990000, 1, 'C02'),
(N'Dell XPS 13', 'xps13.jpg', 28990000, 1, 'C02'),
(N'Sony WH-1000XM5', 'sony1000xm5.jpg', 8990000, 1, 'C03'),
(N'Apple Watch Ultra', 'watchultra.jpg', 21990000, 1, 'C05'),
(N'Cáp sạc Type-C', 'captypec.jpg', 199000, 1, 'C04'),
(N'AirPods Pro 2', 'airpodspro2.jpg', 5990000, 1, 'C03'),
(N'Lenovo ThinkPad X1', 'thinkpadx1.jpg', 34990000, 1, 'C02'),
(N'Samsung Galaxy Buds2', 'buds2.jpg', 2490000, 1, 'C03');

CREATE TABLE Accounts (
    Username NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(100) NOT NULL,
    Fullname NVARCHAR(100),
    Email NVARCHAR(100),
    Photo NVARCHAR(100),
    Activated BIT,
    Admin BIT
);

INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin) VALUES
('admin', '123', N'Quản trị viên', 'admin@webshop.vn', 'admin.jpg', 1, 1),
('user1', '123', N'Nguyễn Văn A', 'a@gmail.com', 'a.jpg', 1, 0),
('user2', '123', N'Trần Thị B', 'b@gmail.com', 'b.jpg', 1, 0),
('user3', '123', N'Lê Văn C', 'c@gmail.com', 'c.jpg', 1, 0),
('user4', '123', N'Phạm Thị D', 'd@gmail.com', 'd.jpg', 1, 0);

CREATE TABLE Orders (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Address NVARCHAR(255),
    CreateDate DATE DEFAULT GETDATE(),
    Username NVARCHAR(50) FOREIGN KEY REFERENCES Accounts(Username)
);

INSERT INTO Orders (Address, Username) VALUES
(N'123 Lê Lợi, Q.1, TP.HCM', 'user1'),
(N'456 Trần Hưng Đạo, Q.5, TP.HCM', 'user2'),
(N'789 Nguyễn Huệ, Q.1, TP.HCM', 'user3');

CREATE TABLE OrderDetails (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Price FLOAT,
    Quantity INT,
    ProductId INT FOREIGN KEY REFERENCES Products(Id),
    OrderId BIGINT FOREIGN KEY REFERENCES Orders(Id)
);

INSERT INTO OrderDetails (Price, Quantity, ProductId, OrderId) VALUES
(29990000, 1, 1, 1),
(23990000, 2, 2, 1),
(8990000, 1, 5, 2),
(21990000, 1, 6, 2),
(199000, 3, 7, 3),
(5990000, 1, 8, 3);
GO
