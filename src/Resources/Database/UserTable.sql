CREATE TABLE IF NOT EXISTS Users(
    UUID varchar(10) NOT NULL PRIMARY KEY,
    Username varchar(10),
    Password varchar(20),
    Snake_HS int,
    Tic_Tac_Toe_Wins int,
    Tic_Tac_Toe_LOSS int,
    Blackjack_WINS int,
    Blackjack_Loss int
);

INSERT INTO Users (UUID, Username, Password, Snake_HS, Tic_Tac_Toe_Wins, Tic_Tac_Toe_LOSS, Blackjack_WINS, Blackjack_Loss)
VALUES
    ('BZU05D2CO0', 'Pikagamer35', 'Pikapika', 0, 0, 0, 0, 0),
    ('MKJ7SG5HV7', 'Everet', 'Mountainman', 0, 0, 0, 0, 0),
    ('9A33HWQXUW', 'Cheerupra', 'Flash', 0, 0, 0, 0, 0),
    ('SHQJFCRKIG', 'Trickedge', 'Treat', 0, 0, 0, 0, 0),
    ('XJ43ZZOSY3', 'Treela', 'Trikru', 0, 0, 0, 0, 0);
