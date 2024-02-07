INSERT INTO fir (code, identifier, name) VALUES
(87582, 'EZE', 'Ezeiza'),
(87344, 'CBA', 'Cordoba'),
(87418, 'DOZ', 'Mendoza'),
(87860, 'CRV', 'Comodoro Rivadavia'),
(87155, 'SIS', 'Resistencia');

INSERT INTO airport (icao, iata, name, fir, hasTAF, hasPronarea) VALUES
('SAEZ', 'EZE', 'Aeropuerto internacional Ministro Pistarini', 87582, true, true),
('SABE', 'AEP', 'Aeroparque Jorge Newbery', 87582, true, false),
('SAZB', 'BCA', 'Aeropuerto Comandante Espora', 87582, true, false),
('SAZS', 'BAR', 'Aeropuerto internacional Teniente Luis Candelaria', 87582, true, false),
('SADO', 'CPO', 'Aeropuerto de Campo de mayo', 87582, true, false),
('SAZY', 'CHP', 'San Martín de los Andes', 87582, true, false),
('SAAC', '', 'Aeropuerto Comodoro Pierrestegui', 87582, false, false),
('SADP', '', 'Aeropuerto El Palomar', 87582, true, false),
('SAZG', 'GPI', 'Aeropuerto de General Pico', 87582, false, false),
('SAAG', 'GUA', 'Aeropuerto de Gualeguaychú', 87582, false, false),
('SAAJ', 'NIN', 'Aeropuerto de Junin', 87582, false, false),
('SAZM', 'MDP', 'Aeropuerto internacional Astor Piazzolla', 87582, true, false),
('SADJ', '', 'Aeropuerto Mariano Moreno', 87582, false, false),
('SADM', 'MOR', 'Aeropuerto de Moron', 87582, false, false),
('SAZN', 'NEU', 'Aeropuerto Rincón de los Sauces', 87582, true, false),
('SAAP', 'PAR', 'Aeropuerto General Justo José de Urquiza', 87582, true, false),
('SAAR', 'ROS', 'Aeropuerto internacional Rosario Islas Malvinas', 87582, true, false),
('SADF', 'FDO', 'Aeropuerto internacional de San Fernando', 87582, true, false),
('SAZR', 'OSA', 'Aeropuerto de Santa Rosa', 87582, true, false),
('SAAV', 'SVO', 'Aeropuerto de Sauce Viejo', 87582, true, false),
('SAZT', 'DIL', 'Aeropuerto de Tandil', 87582, false, false),

('SACO', 'CBA', 'Aeropuerto internacional Ingeniero Ambrosio Taravella', 87344, true, true),
('SANC', 'CAT', 'Aeropuerto Coronel Felipe Varela', 87344, true, false),
('SACE', '', 'Escuela de aviación', 87344, false, false),
('SASJ', 'JUJ', 'Aeropuerto internacional Gobernador Horario Guzmán', 87344, true, false),
('SANL', 'LAR', 'Aeropuerto Capitán Vicente Almados Almonacid', 87344,  true, false),
('SAOC', 'TRC', 'Aeropuerto de Río cuarto', 87344,  true, false),
('SASA', 'SAL', 'Aeropuerto internacional Martín Miguel de Güemes', 87344,  true, false),
('SAOS', '', 'Aeropuerto internacional Valle del Conlara', 87344, false, false),
('SANE', 'SDE', 'Aeropuerto Vicecomodoro Ángel de la Paz Aragonés', 87344,  true, false),
('SANR', '', 'Aeropuerto internacional de Termas de Río Hondo', 87344,  true, false),
('SANT', 'TUC', 'Aeropuerto internacional Teniente Benjamín Matienzo', 87344,  true, false),

('SAME', 'DOZ', 'Aeropuerto internacional El Plumerillo', 87418, true, true),
('SAMM', 'MLG', 'Aeropuerto internacional Comodoro Ricardo Salomón', 87418, false, false),
('SANU', 'JUA', 'Aeropuerto Domingo Faustino Sarmiento', 87418,  true, false),
('SAOU', 'UIS', 'Aeropuerto Brigadier Mayor Cesar Raúl Ojeda', 87418,  true, false),
('SAMR', 'SRA', 'Aeropuerto internacional Suboficial Ayudante Santiago Germano', 87418,  true, false),
('SAOR', 'RYD', 'Aeropuerto de Villa Reynolds', 87418, false, false),

('SARE', 'SIS', 'Aeropuerto internacional de Resistencia', 87155, true, true),
('SARC', 'CRR', 'Aeropuerto internacional Dr. Fernando Piragine Niveyro', 87155,  true, false),
('SARF', 'FSA', 'Aeropuerto internacional de Formosa', 87155,  true, false),
('SARI', 'IGU', 'Aeropuerto internacional de Puerto Iguazú', 87155,  true, false),
('SARL', 'LIB', 'Aeropuerto internacional de Paso de los Libres', 87155, false, false),
('SARP', 'POS', 'Aeropuerto internacional Libertador General José de San Martín', 87155,  true, false),
('SATR', 'RTA', 'Aeropuerto Daniel Jurkic', 87155, false, false),

('SAVC', 'CRV', 'Aeropuerto internacional General Enrique Mosconi', 87860, true, true),
('SAWC', 'ETE', 'Aeropuerto Comandante Armando Tola', 87860,  true, false),
('SAVE', 'EQS', 'Aeropuerto Brigadier General Antonio Parodi', 87860,  true, false),
('SAWP', '', 'Aeropuerto de Perito Moreno', 87860, false, false),
('SAVY', 'PMY', 'Aeropuerto El Tehuelche', 87860, false, false),
('SAWG', 'GAL', 'Aeropuerto internacional Piloto civil Norberto Fernández', 87860,  true, false),
('SAWE', 'GTA', 'Aeropuerto internacional Gobernador Ramón Trejo Noel', 87860, true, false),
('SAWJ', '', 'Aeropuerto Capitán José Daniel Vázquez', 87860, false, false),
('SAWU', '', 'Aeropuerto de Santa Cruz', 87860, false, false),
('SAVT', 'TRE', 'Aeropuerto Almirante Marco Andrés Zar', 87860,  true, false),
('SAWH', 'USU', 'Aeropuerto internacional Malvinas Argentinas', 87860,  true, false),
('SAVV', 'VIE', 'Aeropuerto Gobernador Edgardo Castello', 87860, true, false),

('SAWB', '', 'Base Marambio', 87860, true, true);