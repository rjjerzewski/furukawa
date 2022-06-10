-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 10-Jun-2022 às 20:41
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `apf`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbclientes`
--

CREATE TABLE `tbclientes` (
  `idcli` int(11) NOT NULL,
  `nomecliente` varchar(50) NOT NULL,
  `cpf` varchar(50) NOT NULL,
  `rg` varchar(50) NOT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `fonecli` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `datacad` date DEFAULT NULL,
  `razaosocial` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `pais` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbproduto`
--

CREATE TABLE `tbproduto` (
  `idprod` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `discriminacao` varchar(50) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `unidade` varchar(50) DEFAULT NULL,
  `departamento` varchar(50) DEFAULT NULL,
  `precovenda` varchar(50) DEFAULT NULL,
  `fabricante` varchar(50) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `quantidade` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Extraindo dados da tabela `tbproduto`
--

INSERT INTO `tbproduto` (`idprod`, `nome`, `discriminacao`, `codigo`, `tipo`, `unidade`, `departamento`, `precovenda`, `fabricante`, `marca`, `quantidade`) VALUES
(2, 'TESTE', 'TESTE', '123456789', 'TESTE', 'TESTE', 'TESTE', '25,00', 'TESTE', 'TESTE', '10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbusuarios`
--

CREATE TABLE `tbusuarios` (
  `iduser` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `perfil` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Extraindo dados da tabela `tbusuarios`
--

INSERT INTO `tbusuarios` (`iduser`, `usuario`, `fone`, `login`, `senha`, `perfil`) VALUES
(1, 'Rodrigo Jerzewski', '4599837-3722', 'rodrigo', '260114', 'ADMIN'),
(5, 'vendedor', '45998373722', 'vendedor', '123', 'USER'),
(6, 'Administrador', '4512345678', 'admin', 'admin', 'admin');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tbclientes`
--
ALTER TABLE `tbclientes`
  ADD PRIMARY KEY (`idcli`) USING BTREE;

--
-- Índices para tabela `tbproduto`
--
ALTER TABLE `tbproduto`
  ADD PRIMARY KEY (`idprod`) USING BTREE;

--
-- Índices para tabela `tbusuarios`
--
ALTER TABLE `tbusuarios`
  ADD PRIMARY KEY (`iduser`) USING BTREE,
  ADD UNIQUE KEY `login` (`login`) USING BTREE;

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbclientes`
--
ALTER TABLE `tbclientes`
  MODIFY `idcli` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tbproduto`
--
ALTER TABLE `tbproduto`
  MODIFY `idprod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
