CREATE DATABASE Lab7Java5;
GO
USE Lab7Java5;
GO

CREATE TABLE Categories (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL
);

CREATE TABLE Products (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Price FLOAT NOT NULL,
    CreateDate DATE DEFAULT GETDATE(),
    CategoryId INT FOREIGN KEY REFERENCES Categories(Id)
);

INSERT INTO Categories (Name) VALUES
(N'Áo'),
(N'Quần'),
(N'Giày dép'),
(N'Phụ kiện');
GO

INSERT INTO Products (Name, Price, CreateDate, CategoryId) VALUES
(N'Áo thun trắng basic', 180000, '2025-01-05', 1),
(N'Áo sơ mi công sở', 290000, '2025-02-10', 1),
(N'Áo hoodie unisex', 420000, '2025-03-15', 1),
(N'Áo khoác bomber nam', 550000, '2025-04-20', 1),
(N'Quần jean xanh nam', 350000, '2025-01-12', 2),
(N'Quần short kaki nữ', 270000, '2025-02-22', 2),
(N'Quần tây công sở', 400000, '2025-03-30', 2),
(N'Quần jogger thể thao', 320000, '2025-04-10', 2),
(N'Giày thể thao sneaker', 850000, '2025-01-08', 3),
(N'Giày da nam cao cấp', 1200000, '2025-02-14', 3),
(N'Sandal nữ đi học', 300000, '2025-03-21', 3),
(N'Dép tổ ong huyền thoại', 80000,  '2025-04-25', 3),
(N'Nón lưỡi trai local brand', 150000, '2025-01-17', 4),
(N'Balo thời trang học sinh', 450000, '2025-02-28', 4),
(N'Đồng hồ điện tử thông minh', 950000, '2025-03-25', 4),
(N'Thắt lưng da bò thật', 280000, '2025-04-05', 4);
GO

SELECT * FROM Products;