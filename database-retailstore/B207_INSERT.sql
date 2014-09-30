SET SEARCH_PATH TO Group_B207;

INSERT INTO Genres VALUES(
'GE1',
'Alternative Rock',
'Broad movement born in the 1980s generally relegated to the underground music scene and operating outside of the mainstream');

INSERT INTO Genres VALUES(
'GE2',
'Alternative Metal',
'Catch-all term for heavy metal which uses techniques less conventional in heavy metal');

INSERT INTO Genres VALUES(
'GE3',
'Alternative Hip-Hop',
'Opposite of gangster rap, usually includes metaphorical aware lyrics (also known as alternative rap or Bohemian hip hop)');

INSERT INTO Genres VALUES(
'GE4',
'Blues',
'A hybrid musical genre combining bluesy improvisations over the 12-bar blues and extended boogie jams with rock and roll styles.');

INSERT INTO Genres VALUES(
'GE5',
'Dance',
'Contemporary form of dance music with pop music structures');

INSERT INTO Genres VALUES(
'GE6',
'Filmi',
'Indian film music');

INSERT INTO Genres VALUES(
'GE7',
'Ghazal',
'Vocal form originally Persian but since spread to Central Asia, Iran, Turkey and India');

INSERT INTO Genres VALUES(
'GE8',
'Jazz',
'The word "Jazz" (in early years also spelled "jass") began as a West Coast slang term and was first used to refer to music in Chicago at about 1915');

INSERT INTO Genres VALUES(
'GE9',
'Metal Core',
'Metalcore is a subgenre of heavy metal combining various elements of extreme metal and hardcore punk.');

INSERT INTO Genres VALUES(
'GE10',
'Opera',
'Theatrical performances in which all or most dialogue is sung with musical accompaniment');

INSERT INTO Genres VALUES(
'GE11',
'Action',
'An action game requires players to use quick reflexes, accuracy, and timing to overcome obstacles. It is perhaps the most basic of gaming genres, and certainly one of the broadest. Action games tend to have gameplay with emphasis on combat. There are many subgenres of action games, such as fighting games and first-person shooters.');

INSERT INTO Genres VALUES(
'GE12',
'Shooter',
'A shooter game focuses primarily on combat involving projectile weapons, such as guns and missiles. They can be divided into first-person and third-person shooters, depending on the camera perspective. Some first-person shooters use light gun technology.');

INSERT INTO Genres VALUES(
'GE13',
'Action-Adventure',
'Action-adventure games combine elements of their two component genres, typically featuring long-term obstacles that must be overcome using a tool or item as leverage (which is collected earlier), as well as many smaller obstacles almost constantly in the way, that require elements of action games to overcome.');

INSERT INTO Genres VALUES(
'GE14',
'Adventure',
'Adventure games were some of the earliest games created, beginning with the text adventure Colossal Cave Adventure in the 1970s. That game was originally titled simply "Adventure," and is the namesake of the genre. Over time, graphics have been introduced to the genre and the interface has evolved.');

INSERT INTO Genres VALUES(
'GE15',
'Role Playing',
'Role-playing video games draw their gameplay from traditional role-playing games like Dungeons & Dragons. Most cast the player in the role of one or more "adventurers" who specialize in specific skill sets (such as melee combat or casting magic spells) while progressing through a predetermined storyline.');

INSERT INTO Genres VALUES(
'GE16',
'Simulation',
'Role-playing video games draw their gameplay from traditional role-playing games like Dungeons & Dragons. Most cast the player in the role of one or more "adventurers" who specialize in specific skill sets (such as melee combat or casting magic spells) while progressing through a predetermined storyline.');

INSERT INTO Genres VALUES(
'GE17',
'Strategy',
'Strategy video games focus on gameplay requiring careful and skillful thinking and planning in order to achieve victory. In most strategy video games, says Andrew Rollings, "the player is given a godlike view of the game world, indirectly controlling the units under his command."');

INSERT INTO Genres VALUES(
'GE18',
'Sports',
'Electronic Sports games are multiplayer games that are usually played competitively at the professional level. These games are often targeted at the "hardcore" gaming audience, and are usually first-person shooter games, requiring twitch-based reaction speed and coordination, or real-time strategy games, requiring high levels of strategic macro- and micromanagement.');

INSERT INTO Genres VALUES(
'GE19',
'Arcade',
'An arcade game is a coin-operated entertainment machine, usually installed in public businesses such as restaurants, bars, and amusement arcades. Most arcade games are video games, pinball machines, electro-mechanical games, redemption games, and merchandisers (such as claw cranes).');

INSERT INTO Genres VALUES(
'GE20',
'Electronic Dance Music',
'Electronic dance music (EDM) is electronic music produced primarily for the purposes of use within a nightclub setting, or in an environment that is centered upon dance-based entertainment. The music is largely created for use by disc jockeys and is produced with the intention of it being heard in the context of a continuous DJ set; wherein the DJ progresses from one record to the next via a synchronized segue or "mix".');

INSERT INTO Genres VALUES(
'GE21',
'Rock',
'Rock music is a genre of popular music that developed during and after the 1960s, particularly in the United Kingdom and the United States. It has its roots in 1940s and 1950s rock and roll, itself heavily influenced by rhythm and blues and country music. Rock music also drew strongly on a number of other genres such as blues and folk, and incorporated influences from jazz, classical and other musical sources.');

INSERT INTO Genres VALUES(
'GE22',
'Hindi Solos',
'Albums released by Indian Artists mainly comprising a fusion of Rock Music with Hindi Lyrics');


INSERT INTO Games VALUES(
'GM1',
'FIFA 12',
NULL,
'PlayStation 3',
'GE18',
2011,
9.2,
500,
200,
74.99,
250);

INSERT INTO Games VALUES(
'GM2',
'Pinball Construction Set',
NULL,
'Windows',
'GE19',
1983,
7.5,
10,
0,
1.99,
2);

INSERT INTO Games VALUES(
'GM3',
'Half Life: Counter Strike',
NULL,
'Windows',
'GE12',
2000,
9.0,
25,
3,
4.99,
97);

INSERT INTO Games VALUES(
'GM4',
'Prince Of Persia Warrior Within',
NULL,
'Windows',
'GE11',
2004,
8.3,
50,
15,
4.99,
250);

INSERT INTO Games VALUES(
'GM5',
'Mafia',
NULL,
'Windows',
'GE13',
2002,
9.3,
49,
6,
6.99,
250);

INSERT INTO Games VALUES(
'GM6',
'Railroad Tycoon 2',
NULL,
'Windows',
'GE17',
1998,
8.9,
49,
6,
6.99,
250);


INSERT INTO Developers VALUES(
'DV1',
'Electronic Arts',
1982,
1293, 'GM2');

INSERT INTO Developers VALUES(
'DV2',
'Sierra Entertainment',
1979,
231, 'GM3');

INSERT INTO Developers VALUES(
'DV3',
'Ubisoft',
1986,
1103, 'GM4');

INSERT INTO Developers VALUES(
'DV4',
'Gathering Of Developers',
1998,
55, 'GM6');


UPDATE Games SET Developer_ID = 'DV1' WHERE Game_ID = 'GM1';
UPDATE Games SET Developer_ID = 'DV1' WHERE Game_ID = 'GM2';
UPDATE Games SET Developer_ID = 'DV2' WHERE Game_ID = 'GM3';
UPDATE Games SET Developer_ID = 'DV3' WHERE Game_ID = 'GM4';
UPDATE Games SET Developer_ID = 'DV4' WHERE Game_ID = 'GM5';
UPDATE Games SET Developer_ID = 'DV4' WHERE Game_ID = 'GM6';


INSERT INTO Movies VALUES(
'MO1',
'The Shawshank Redemption',
NULL,
NULL,
1994,
25,
9.2,
100,
20,
24.99,
50, 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.');

INSERT INTO Movies VALUES(
'MO2',
'Fight Club',
NULL,
NULL,
1999,
63,
8.8,
100,
20,
22.99,
50, 'An office employee and a soap salesman build a global organization to help vent male aggression.');

INSERT INTO Movies VALUES(
'MO3',
'Se7en',
NULL,
NULL,
1995,
33,
8.7,
100,
20,
23.99,
50, 'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi.');

INSERT INTO Movies VALUES(
'MO4',
'The Curious Case Of Benjamin Button',
NULL,
NULL,
2008,
150,
7.9,
65,
40,
17.99,
50, 'Tells the story of Benjamin Button, a man who starts aging backwards with bizarre consequences.');

INSERT INTO Movies VALUES(
'MO5',
'The Lord of the Rings: The Fellowship of the Ring',
NULL,
NULL,
2001,
93,
8.8,
65,
40,
13.99,
50, 'In a small village in the Shire a young Hobbit named Frodo has been entrusted with an ancient Ring. Now he must embark on an Epic quest to the Cracks of Doom in order to destroy it.');

INSERT INTO Movies VALUES(
'MO6',
'X-Men',
NULL,
NULL,
2000,
75,
7.4,
50,
15,
9.99,
50, 'Two mutants come to a private academy for their kind whose resident superhero team must oppose a terrorist organization with similar powers.');

INSERT INTO Movies VALUES(
'MO7',
'The Usual Suspects ',
NULL,
NULL,
1995,
6,
8.7,
90,
30,
19.99,
150, 'A boat has been destroyed, criminals are dead, and the key to this mystery lies with the only survivor and his twisted, convoluted story beginning with five career crooks in a seemingly random police lineup.');


INSERT INTO Directors VALUES(
'DI1',
'Frank Darabont',
'01-29-1959',
'France',
'MO1',
10, 'Three-time Oscar nominee Frank Darabont was born in a refugee camp in 1959 in Montbeliard, France, the son of Hungarian parents who had fled Budapest during the failed 1956 Hungarian revolution.');

INSERT INTO Directors VALUES(
'DI2',
'David Fincher',
'08-28-1962',
'USA',
'MO3',
22, 'David Fincher was born in 1962 in Denver, Colorado, and was raised in Marin County, California. When he was 18 years old he went to work for John Korty at Korty Films in Mill Valley. He subsequently worked at ILM (Industrial Light and Magic) from 1981-1983.');

INSERT INTO Directors VALUES(
'DI3',
'Peter Jackson',
'10-31-1961',
'New Zealand',
'MO5',
17, 'Peter Jackson was born as an only child in a small coast-side town in New Zealand in 1961. When a friend of his parents bought him a super 8mm movie camera (because she saw how much he enjoyed taking photos), the then eight-year-old Peter instantly grabbed the thing to start recording his own movies, which he made with his friends.');

INSERT INTO Directors VALUES(
'DI4',
'Bryan Singer',
'09-17-1965',
'USA',
'MO7',
12, 'Cousin of actors Marc Singer and Lori Singer.');


INSERT INTO Actors VALUES(
'AC1',
'Tim Robbins',
'10-16-1958',
'USA',
'MO1',
66, 'Born in West Covina, California, but raised in New York City, Tim Robbins is the son of former The Highwaymen singer Gil Robbins and actress Mary Robbins. Robbins studied drama at UCLA, where he graduated with honors in 1981.');

INSERT INTO Actors VALUES(
'AC2',
'Morgan Freeman',
'06-1-1937',
'USA',
'MO1',
96, 'With an authoritative voice and calm demeanour, this ever popular American actor has grown into one of the most respected figures in modern US cinema.');

INSERT INTO Actors VALUES(
'AC3',
'Brad Pitt',
'12-18-1963',
'USA',
'MO3',
64, 'Brad Pitt was born in 1963 in Oklahoma and raised in Springfield, Missouri. His mother"s name is Jane Etta Hillhouse. His father, William (Bill) Pitt, worked in management at a trucking firm in Springfield.');

INSERT INTO Actors VALUES(
'AC4',
'Edward Norton',
'08-18-1969',
'USA',
'MO2',
28, 'Edward Norton was born on August 18, 1969 to parents Edward, an attorney who works for the National Trust for Historic Preservation and Robin Norton, a former foundation executive and teacher who passed away of brain cancer on March 6, 1997.');

INSERT INTO Actors VALUES(
'AC5',
'Cate Blanchett',
'05-14-1969',
'Australia',
'MO5',
44, 'Cate Blanchett was born on May 14, 1969 in Australia to an American father and an Australian mother. She has an older brother and an younger sister. When she was ten years old, her 40-year old father died of a sudden heart attack.');

INSERT INTO Actors VALUES(
'AC6',
'Elijah Wood',
'01-28-1981',
'USA',
'MO5',
69, 'Elijah Jordan Wood was born on January 28, 1981, in Cedar Rapids, Iowa, to Warren and Debbie Wood. He has an older brother Zack and a younger sister Hannah Wood. At an early age Elijah showed a knack for entertaining and wowing audiences.');

INSERT INTO Actors VALUES(
'AC7',
'Ian McKellen',
'05-25-1939',
'United Kingdom',
'MO6',
87, 'On May 25th, 1939, in the town of Burnley in northern England, Ian Murray McKellen was born. His parents, Denis and Margery, soon moved with Ian and his sister Jean to the coal mining town of Wigan. It was in this small town that young Ian rode out World War II.');

INSERT INTO Actors VALUES(
'AC8',
'Hugh Jackman',
'10-12-1968',
'Australia',
'MO6',
42, 'Born in Sydney of English parentage, and the youngest of five children, Jackman has a communications degree with a journalism major from the University of Technology Sydney.');

INSERT INTO Actors VALUES(
'AC9',
'Patrick Stewart',
'07-13-1940',
'United Kingdom',
'MO6',
117, 'Born Mirfield, Yorkshire, England. Member of various local drama groups from about age 12. Left school at 15 to work as junior reporter on local paper; quit when Editor told him he was spending too much time at the theatre and not enough working.'); 

INSERT INTO Actors VALUES(
'AC10',
'Kevin Spacey',
'07-26-1959',
'USA',
'MO7',
66, 'As enigmatic as he is talented, Kevin Spacey has always kept the details of his private life closely guarded. As he explained in a 1998 interview with the London Evening Standard, "Its not that I want to create some bullshit mystique by maintaining a silence about my personal life, it is just that the less you know about me.');

INSERT INTO Actors VALUES(
'AC11',
'Gabriel Byrne',
'05-12-1950',
'Ireland',
'MO7',
87, 'Byrne was the first of six children, born in Dublin, Ireland. His father was a cooper and his mother a hospital worker. He was raised Catholic and educated by the Irish Christian Brothers. He spent five years of his childhood in a seminary training to be a Catholic priest.');


INSERT INTO Production_House VALUES(
'PH1',
'Castle Rock Entertainment',
1987,
'USA',
298);

INSERT INTO Production_House VALUES(
'PH2',
'New Line Cinema',
1967,
'USA',
316);

INSERT INTO Production_House VALUES(
'PH3',
'Regency Enterprises',
1982,
'USA',
100);

INSERT INTO Production_House VALUES(
'PH4',
'Warner Bros. Pictures',
1918,
'USA',
3821);

INSERT INTO Production_House VALUES(
'PH5',
'Twentieth Century Fox',
1935,
'USA',
1488);

INSERT INTO Production_House VALUES(
'PH6',
'PolyGram Filmed Entertainment',
1979,
'England',
71);


UPDATE Movies SET Director_ID = 'DI1' WHERE Movie_ID = 'MO1';
UPDATE Movies SET Prod_House_ID = 'PH1' WHERE Movie_ID = 'MO1';

UPDATE Movies SET Director_ID = 'DI2' WHERE Movie_ID = 'MO2';
UPDATE Movies SET Prod_House_ID = 'PH3' WHERE Movie_ID = 'MO2';

UPDATE Movies SET Director_ID = 'DI2' WHERE Movie_ID = 'MO3';
UPDATE Movies SET Prod_House_ID = 'PH2' WHERE Movie_ID = 'MO3';

UPDATE Movies SET Director_ID = 'DI2' WHERE Movie_ID = 'MO4';
UPDATE Movies SET Prod_House_ID = 'PH4' WHERE Movie_ID = 'MO4';

UPDATE Movies SET Director_ID = 'DI3' WHERE Movie_ID = 'MO5';
UPDATE Movies SET Prod_House_ID = 'PH2' WHERE Movie_ID = 'MO5';

UPDATE Movies SET Director_ID = 'DI4' WHERE Movie_ID = 'MO6';
UPDATE Movies SET Prod_House_ID = 'PH5' WHERE Movie_ID = 'MO6';

UPDATE Movies SET Director_ID = 'DI4' WHERE Movie_ID = 'MO7';
UPDATE Movies SET Prod_House_ID = 'PH6' WHERE Movie_ID = 'MO7';


INSERT INTO Acted_In VALUES(
'AC1',
'MO1',
'Lead Actor');

INSERT INTO Acted_In VALUES(
'AC2',
'MO1',
'Supporting Actor');

INSERT INTO Acted_In VALUES(
'AC3',
'MO2',
'Lead Actor');

INSERT INTO Acted_In VALUES(
'AC4',
'MO2',
'Supporting Actor');

INSERT INTO Acted_In VALUES(
'AC3',
'MO3',
'Lead Actor');

INSERT INTO Acted_In VALUES(
'AC2',
'MO3',
'Supporting Actor');

INSERT INTO Acted_In VALUES(
'AC3',
'MO4',
'Lead Actor');

INSERT INTO Acted_In VALUES(
'AC5',
'MO4',
'Lead Actress');

INSERT INTO Acted_In VALUES(
'AC6',
'MO5',
'Lead Actor');

INSERT INTO Acted_In VALUES(
'AC7',
'MO5',
'Supporting Actor');

INSERT INTO Acted_In VALUES(
'AC5',
'MO5',
'Lead Actress');

INSERT INTO Acted_In VALUES(
'AC8',
'MO6',
'Lead Actor');

INSERT INTO Acted_In VALUES(
'AC9',
'MO6',
'Supporting Actor');

INSERT INTO Acted_In VALUES(
'AC10',
'MO7',
'Lead Actor');

INSERT INTO Acted_In VALUES(
'AC11',
'MO7',
'Supporting Actor');


INSERT INTO Audio VALUES(
'AU1',
'Meteora',
NULL,
'GE1',
2003,
60,
15,
1.99,
50);

INSERT INTO Audio VALUES(
'AU2',
'Hybrid Theory',
NULL,
'GE1',
2000,
100,
57,
1.99,
69);

INSERT INTO Audio VALUES(
'AU3',
'In My Memory',
NULL,
'GE20',
2001,
250,
60,
4.99,
850);

INSERT INTO Audio VALUES(
'AU4',
'Please Please Me',
NULL,
'GE21',
1963,
200,
25,
4.99,
1500);

INSERT INTO Audio VALUES(
'AU5',
'Bhula Do',
NULL,
'GE21',
2006,
50,
15,
1.99,
156);



INSERT INTO Artists VALUES(
'AR1',
'Linkin Park',
'USA',
'AU2', 'Linkin Park is an American rock band from Agoura Hills, California. Formed in 1996, the band rose to international fame with their debut album, Hybrid Theory, which was certified Diamond by the RIAA in 2005 and multi-platinum in several other countries. Its following studio album, Meteora, continued the band"s success, topping the Billboard 200 album chart in 2003, and was followed by extensive touring and charity work around the world.');

INSERT INTO Artists VALUES(
'AR2',
'Tiesto',
'Netherlands',
'AU3', 'Tijs Michiel Verwest, born 17 January 1969),[1] known as Tiësto, is a Dutch musician, DJ and record producer of electronic dance music. Although he has used many aliases in the past, he is best known for his work as DJ Tiësto. On his latest productions, however, he has dropped the "DJ" label and is now known simply as "Tiësto",[2] an alias which is a twist of his childhood nickname.');

INSERT INTO Artists VALUES(
'AR3',
'The Beatles',
'England',
'AU4', 'The Beatles were an English rock band, active throughout the 1960s and one of the most commercially successful and critically acclaimed acts in the history of popular music.[1] Formed in Liverpool, by 1962 the group consisted of John Lennon (rhythm guitar, vocals), Paul McCartney (bass guitar, vocals), George Harrison (lead guitar, vocals) and Ringo Starr (drums, vocals).');

INSERT INTO Artists VALUES(
'AR4',
'Raeth',
'Pakistan',
'AU5', 'The true strength of Raeth as a band lies in their uncanny ability to create personal songs. Each song on this album bears their signature blend of meaningful lyrics, kick-ass vocals and rocking sound. The first single, Bhula Do completely showcases their talent, paving the way for the rest of the songs on this truly excellent album.');


UPDATE Audio SET Artist_ID = 'AR1' WHERE Audio_ID = 'AU1';
UPDATE Audio SET Artist_ID = 'AR1' WHERE Audio_ID = 'AU2';
UPDATE Audio SET Artist_ID = 'AR2' WHERE Audio_ID = 'AU3';
UPDATE Audio SET Artist_ID = 'AR3' WHERE Audio_ID = 'AU4';
UPDATE Audio SET Artist_ID = 'AR4' WHERE Audio_ID = 'AU5';


INSERT INTO Suppliers VALUES(
'SP1',
'Sharma Movies',
'Kandivali, East, Mumbai',
8758410931,
'Movies');

INSERT INTO Suppliers VALUES(
'SP2',
'India Games',
'Connaught Place, New Delhi',
9856232145,
'Games');

INSERT INTO Suppliers VALUES(
'SP3',
'Tanishka Entertainment',
'Vaishali Nagar, Jaipur',
9829115621,
'Audio');
