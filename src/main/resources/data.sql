INSERT INTO fir (identifier, name, wmo) VALUES
('EZE', 'Ezeiza', 87582),
('CBA', 'Cordoba', 87344),
('DOZ', 'Mendoza', 87418),
('CRV', 'Comodoro Rivadavia', 87860),
('SIS', 'Resistencia', 87155);

INSERT INTO province (name) VALUES
('Ciudad autónoma de Buenos Aires'),
('Buenos Aires'),
('Catamarca'),
('Chaco'),
('Chubut'),
('Córdoba'),
('Corrientes'),
('Entre Ríos'),
('Formosa'),
('Jujuy'),
('La Pampa'),
('La Rioja'),
('Mendoza'),
('Misiones'),
('Neuquén'),
('Río Negro'),
('Salta'),
('San Juan'),
('San Luis'),
('Santa Cruz'),
('Santa Fe'),
('Santiago del Estero'),
('Tierra del Fuego, Antártida e Islas del Atlántico Sur'),
('Tucumán');

-- FIR EZE
INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAEZ', 'EZE', 'EZE', 87576, 'Aeropuerto Internacional Ministro Pistarini', 'EZE', 'Ezeiza', 'Buenos Aires', true, 20,  -34.8167, -58.5333);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('11/29', 3300, 60, 'Asfalto', 'SAEZ'), ('17/35', 3105, 45, 'Asfalto', 'SAEZ');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SABE', 'AEP', 'AER', 87582, 'Aeroparque Jorge Newbery', 'EZE', 'CABA', 'Ciudad autónoma de Buenos Aires', true, 6, -34.5593, -58.414);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('13/31', 2350, 45, 'Concreto', 'SABE');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZB', 'BHI', 'BCA', 87750, 'Base aeronaval Comandante Espora', 'EZE', 'Bahía Blanca', 'Buenos Aires', true, 75, -38.7333, -62.1667);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('17L/35R', 2410, 45, 'Asfalto', 'SAZB'), ('06/24', 1980, 30, 'Asfalto', 'SAZB'), ('17R/35L', 1498, 30, 'Asfalto', 'SAZB');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZS', 'BCR', 'BAR', 87765, 'Aeropuerto Internacional Teniente Luis Candelaria', 'EZE', 'Bariloche', 'Río Negro', true, 845, -41.15, -71.1667);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('11/29', 2348, 48, 'Concreto', 'SAZS');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SADO', '-', 'CPO', 87570, 'Aeropuerto de Campo de mayo', 'EZE', 'Campo de mayo', 'Buenos Aires', false, 24, -34.5344, -58.6717);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('18/36', 1800, 30, 'Asfalto', 'SADO');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZY', 'CPC', 'CHP', 87761, 'Aeropuerto aviador Carlos Campos', 'EZE', 'San Martín de los Andes', 'Neuquén', false, 783, -40.0757, -71.1339);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('06/24', 2501, 45, 'Asfalto', 'SAZY');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAAC', 'COC', 'DIA', 87395, 'Aeropuerto Comodoro Pierrestegui', 'EZE', 'Concordia', 'Entre Ríos', false, 34, -31.2975, -57.9962);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('04/22', 1600, 30, 'Asfalto', 'SAAC');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SADP', 'EPA', 'PAL', 87571, 'Aeropuerto El Palomar', 'EZE', 'El Palomar', 'Buenos Aires', true, 18, -34.6085, -58.609);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('17/35', 2110, 45, 'Concreto', 'SADP');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZG', 'GPO', 'GPI', 87532, 'Aeropuerto de General Pico', 'EZE', 'General Pico', 'La Pampa' , false, 140, -35.6974, -63.7598);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('16/34', 2350, 45, 'Asfalto', 'SAZG');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAAG', 'GHU', 'GUA', 87497, 'Aeropuerto de Gualeguaychú', 'EZE', 'Gualeguaychú', 'Entre Ríos', false, 23, -33.0058, -58.6147);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('01/19', 1330, 30, 'Asfalto', 'SAAG');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAAJ', 'JNI', 'NIN', 87548, 'Aeropuerto de Junín', 'EZE', 'Junín', 'Buenos Aires', false, 80, -34.5474, -60.9309);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('18/36', 1500, 30, 'Asfalto', 'SAAJ');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZM', 'MDQ', 'MDP', 87692, 'Aeropuerto Internacional Astor Piazzolla', 'EZE', 'Mar del Plata', 'Buenos Aires', true, 18, -37.9358, -57.5759);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('13/31', 2200, 45, 'Concreto', 'SAZM');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SADJ', '-', 'ENO', 87572, 'Aeropuerto Mariano Moreno', 'EZE', 'José C. Paz', 'Buenos Aires', false, 28, -34.5621, -58.786);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('16/34', 2403, 40, 'Asfalto', 'SADJ');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SADM', '-', 'MOR', 87574,'Aeropuerto de Morón', 'EZE', 'Morón', 'Buenos Aires', false, 30, -34.6811, -58.6461);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 2303, 38, 'Asfalto/Concreto', 'SADM');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZN', 'NQN', 'NEU', 87715, 'Aeropuerto Internacional Presidente Perón', 'EZE', 'Neuquén', 'Neuquén', true, 270, -38.9511, -68.1553);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('09/27', 2507, 45, 'Asfalto', 'SAZN');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAAP', 'PRA', 'PAR', 87374, 'Aeropuerto General Justo José de Urquiza', 'EZE', 'Paraná', 'Entre Ríos', true, 74, -31.7962, -60.4812);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 2100, 45, 'Asfalto', 'SAAP');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAAR', 'ROS', 'ROS', 87480, 'Aeropuerto Internacional Rosario Islas Malvinas', 'EZE', 'Rosario', 'Santa Fe', true, 25, -32.9039, -60.7792);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 3000, 45, 'Concreto', 'SAAR');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SADF', 'FDO', 'FDO', 87553, 'Aeropuerto Internacional de San Fernando', 'EZE', 'San Fernando', 'Buenos Aires', true, 3, -34.4542, -58.5861);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('05/23', 1690, 30, 'Asfalto', 'SADF');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZR', 'RSA', 'OSA', 87623, 'Aeropuerto de Santa Rosa', 'EZE', 'Santa Rosa', 'La Pampa', true, 190, -36.5667, -64.2667);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('01/19', 2300, 30, 'Asfalto', 'SAZR');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAAV', 'SFN', 'SVO', 87371, 'Aeropuerto de Sauce Viejo', 'EZE', 'Santa Fe', 'Santa Fe', true, 17, -31.71, -60.8094);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('03/21', 2325, 30, 'Asfalto', 'SAAV');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAZT', 'TDL', 'DIL', 87645, 'Aeropuerto Héroes de Malvinas', 'EZE', 'Tandil', 'Buenos Aires', false, 175, -37.2379, -59.2338);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('01/19', 2550, 48, 'Concreto', 'SAZT');


-- FIR CBA
INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SACO', 'COR', 'CBA', 87344, 'Aeropuerto Internacional Ingeniero Ambrosio Taravella', 'CBA', 'Córdoba', 'Córdoba', true, 484, -31.3121, -64.2084);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('05/23', 2200, 45, 'Asfalto', 'SACO'), ('01/19', 3200, 45, 'Concreto', 'SACO');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SANC', 'CTC', 'CAT', 87222, 'Aeropuerto Internacional Coronel Felipe Varela', 'CBA', 'San Fernando del Valle de Catamarca', 'Catamarca', true, 454, -28.5948, -65.7519);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 2800, 30, 'Asfalto', 'SANC');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SACE', '-', '-', 87347, 'Escuela de Aviación Militar', 'CBA', 'Córdoba', 'Córdoba', false, 502, -31.4461, -64.2779);


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SASJ', 'JUJ', 'JUJ', 87046, 'Aeropuerto Internacional Gobernador Horario Guzmán', 'CBA', 'Perico', 'Jujuy', true, 921, -24.3959, -65.0932);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('16/34', 2944, 40, 'Asfalto', 'SASJ');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SANL', 'IRJ', 'LAR', 87217, 'Aeropuerto Capitán Vicente Almados Almonacid', 'CBA', 'La Rioja', 'La Rioja',  true, 438, -29.3811, -66.7948);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('03/21', 2806, 30, 'Asfalto', 'SANL');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAOC', 'RCU', 'TRC', 87453, 'Aeropuerto de Río cuarto', 'CBA', 'Río Cuarto', 'Córdoba', true, 420, -33.0931, -64.2691);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('05/23', 2265, 45, 'Asfalto', 'SAOC'), ('18/36', 2075, 40, 'Asfalto', 'SAOC');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SASA', 'SLA', 'SAL', 87047, 'Aeropuerto Internacional Martín Miguel de Güemes', 'CBA', 'Salta', 'Salta', true, 1238, -24.8558, -65.4866);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 2989, 45, 'Asfalto', 'SASA'), ('06/24', 2400, 30, 'Asfalto', 'SASA');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAOS', 'RLO', 'SRC', 87444, 'Aeropuerto Internacional Valle del Conlara', 'CBA', 'Santa Rosa del Conlara', 'San Luis', false, 616, -32.385, -65.1829);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 2550, 45, 'Concreto', 'SAOS');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SANE', 'SDE', 'SDE', 87129, 'Aeropuerto Vicecomodoro Aragonés', 'CBA', 'Santiago del Estero', 'Santiago del Estero',  true, 198, -27.7667, -64.3);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('03/21', 2435, 45, 'Asfalto', 'SANE');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SANR', '-', 'TRH', 87127, 'Aeropuerto Internacional de Termas de Río Hondo', 'CBA', 'Termas de Río Hondo', 'Santiago del Estero', true, 260, -27.4981, -64.9356);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('01/19', 2500, 30, 'Asfalto', 'SANR');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SANT', 'TUC', 'TUC', 87121, 'Aeropuerto Internacional Teniente Matienzo', 'CBA', 'San Miguel de Tucumán', 'Tucumán',  true, 440, -26.8364, -65.1057);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 3500, 45, 'Concreto', 'SANT');

-- FIR DOZ
INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAME', 'MDZ', 'DOZ', 87418, 'Aeropuerto Internacional El Plumerillo', 'DOZ', 'Mendoza', 'Mendoza', true, 705, -32.8317, -68.7928);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('18/36', 2835, 54, 'Concreto', 'SAME');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAMM', 'LGS', 'MLG', 87506, 'Aeropuerto Internacional Comodoro Salomón', 'DOZ', 'Malargüe', 'Mendoza', false, 1426, -35.4875, -69.5756);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('09/27', 1450, 20, 'Asfalto', 'SAMM'), ('14/32', 2650, 30, 'Asfalto', 'SAMM');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SANU', 'UAQ', 'JUA', 87311, 'Aeropuerto Domingo Faustino Sarmiento', 'DOZ', 'San Juan', 'San Juan', true, 597, -31.5713, -68.4193);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('18/36', 2460, 45, 'Asfalto', 'SANU');

INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAOU', 'LUQ', 'UIS', 87436, 'Aeropuerto Brigadier Mayor Ojeda', 'DOZ', 'San Luis', 'San Luis',  true, 710, -33.2747, -66.3548);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('01/19', 2995, 30, 'Asfalto', 'SAOU');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAMR', 'AFA', 'SRA', 87509, 'Aeropuerto Internacional Suboficial Ayudante Germano', 'DOZ', 'San Rafael', 'Mendoza', true, 745, -34.5883, -68.4026);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('11/29', 2102, 30, 'Asfalto', 'SAMR');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAOR', 'VME', 'RYD', 87448, 'Aeropuerto de Villa Reynolds', 'DOZ', 'Villa Mercedes', 'San Luis', false, 485, -33.7279, -65.3753);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('06/24', 2362, 50, 'Asfalto', 'SAOR'), ('10/28', 2397, 45, 'Asfalto', 'SAOR');


-- FIR SIS
INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SARE', 'RES', 'SIS', 87155, 'Aeropuerto Internacional de Resistencia', 'SIS', 'Resistencia', 'Chaco', true, 53, -27.45, -59.05);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('03/21', 2770, 45, 'Asfalto', 'SARE');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SARC', 'CNQ', 'CRR', 87166, 'Aeropuerto Internacional Dr. Piragine Niveyro', 'SIS', 'Corrientes', 'Corrientes',  true, 62, -27.4457, -58.7596);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 2250, 45, 'Concreto', 'SARC');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SARF', 'FMA', 'FSA', 87162, 'Aeropuerto Internacional de Formosa', 'SIS', 'Formosa', 'Formosa', true, 59, -26.2138, -58.2285);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('04/22', 1800, 30, 'Asfalto', 'SARF');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SARI', 'IGR', 'IGU', 87097, 'Aeropuerto Internacional Cataratas del Iguazú', 'SIS', 'Puerto Iguazú', 'Misiones',  true, 270, -25.737, -54.4742);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('13/31', 3300, 45, 'Asfalto', 'SARI');

INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SARL', 'AOL', 'LIB', 87289, 'Aeropuerto Internacional de Paso de los Libres', 'SIS', 'Paso de los Libres', 'Corrientes', false, 69, -29.6833, -57.15);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('18/36', 2260, 45, 'Asfalto', 'SARL');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SARP', 'PSS', 'POS', 87178, 'Aeropuerto Internacional Libertador General José de San Martín', 'SIS', 'Posadas', 'Misiones', true, 131, -27.3858, -55.9705);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 2252, 30, 'Asfalto/Concreto', 'SARP');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SATR', 'RCQ', 'RTA', 87270, 'Aeropuerto Teniente Jurkic', 'SIS', 'Reconquista', 'Santa Fe', false, 48, -29.2029, -59.6821);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('02/20', 1250, 34, 'Concreto', 'SATR'), ('10/28', 2800, 50, 'Concreto', 'SATR');

-- FIR CRV
INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAVC', 'CRD', 'CRV', 87860, 'Aeropuerto Internacional General Mosconi', 'CRV', 'Comodoro Rivadavia', 'Chubut', true, 58, -45.7852, -67.4656);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('07/25', 2760, 50, 'Concreto', 'SAVC');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWC', 'FTE', 'ETE', 87904, 'Aeropuerto Internacional Comandante Tola', 'CRV', 'El Calafate', 'Santa Cruz', true, 199, -50.2803, -72.0532);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('07/25', 2550, 45, 'Concreto', 'SAWC');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAVE', 'EQS', 'EQS', 87803, 'Aeropuerto Brigadier General Parodi', 'CRV', 'Esquel', 'Chubut', true, 789, -42.9076, -71.1431);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('05/23', 2393, 40, 'Asfalto', 'SAVE'), ('08/26', 1239, 30, 'Tierra', 'SAVE');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWP', 'PMQ', 'PTM', 87852, 'Aeropuerto de Jalil Hamer', 'CRV', 'Perito Moreno', 'Santa Cruz', false, 429, -46.5377, -70.9789);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('10/28', 1700, 30, 'Asfalto', 'SAWP');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAVY', 'PMY', 'PMY', 97789, 'Aeropuerto El Tehuelche', 'CRV', 'Puerto Madryn', 'Chubut', false, 136, -42.7588, -65.102);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('06/24', 2500, 45, 'Asfalto', 'SAVY');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWG', 'RGL', 'GAL', 87925, 'Aeropuerto Internacional Piloto Civil Norberto Fernández', 'CRV', 'Río Gallegos', 'Santa Cruz', true, 20, -51.6087, -69.3073);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('07/25', 3549, 45, 'Asfalto', 'SAWG');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWE', 'RGA', 'GTA', 87934, 'Aeropuerto Internacional Gobernador Ramón Trejo Noel', 'CRV', 'Río Grande', 'Tierra del Fuego, Antártida e Islas del Atlántico Sur', true, 13, -53.7775, -67.7496);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('08/26', 2000, 45, 'Asfalto', 'SAWE');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWJ', 'ULA', 'SJU', 87909, 'Aeropuerto Capitán Vázquez', 'CRV', 'Puerto San Julián', 'Santa Cruz', false, 58, -49.3167, -67.75);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('07/25', 2000, 40, 'Hormigón', 'SAWJ');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWU', 'RZA', 'SCZ', 87912, 'Aeropuerto de Santa Cruz', 'CRV', 'Puerto Santa Cruz', 'Santa Cruz', false, 58, -50.017, -68.5789);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('07/25', 2000, 30, 'Asfalto', 'SAWU');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAVT', 'REL', 'TRE', 87828, 'Aeropuerto Almirante Marco Andrés Zar', 'CRV', 'Trelew', 'Chubut', true, 39, -43.2104, -65.2699);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('07/25', 2560, 45, 'Asfalto', 'SAVT');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWH', 'USH', 'USU', 87938, 'Aeropuerto Internacional Malvinas Argentinas', 'CRV', 'Ushuaia', 'Santa Cruz',  true, 16, -54.8432, -68.2958);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('07/25', 2804, 45, 'Concreto', 'SAWH');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAVV', 'VDM', 'VIE', 87791, 'Aeropuerto Gobernador Edgardo Castello', 'CRV', 'Viedma', 'Río Negro', true, 6, -40.8661, -62.9983);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('11/29', 2550, 45, 'Asfalto', 'SAVV');


INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SAWB', '-', 'MRO', 89055, 'Aeropuerto de Base Marambio', 'CRV', 'Base Marambio', 'Tierra del Fuego, Antártida e Islas del Atlántico Sur', true, 198, -64.2392, -56.6309);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('05/23', 1208, 40, 'Grava', 'SAWB');

INSERT INTO airport (icao, iata, anac, wmo, name, fir, city, province, hasTAF, elevation, latitude, longitude) VALUES
('SFAL', 'PSY', '-', 88891, 'Aeropuerto de Puerto Argentino', 'CRV', 'Puerto Argentino', 'Tierra del Fuego, Antártida e Islas del Atlántico Sur', true, 75, -51.6859, -57.7784);
INSERT INTO runway (designator, length, width, surface, airport) VALUES
('09/27', 918, 19, 'Asfalto', 'SFAL');
