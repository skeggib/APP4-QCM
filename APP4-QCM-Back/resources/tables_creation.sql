DROP TABLE IF EXISTS Question;
DROP TABLE IF EXISTS Session;

CREATE TABLE Session(
    session_name VARCHAR NOT NULL,
    PRIMARY KEY(session_name)
);
CREATE TABLE Question(
    question_id         INTEGER NOT NULL,
    question_text       TEXT NOT NULL,
    question_response1  TEXT NOT NULL,
    question_response2  TEXT NOT NULL,
    question_response3  TEXT NOT NULL,
    question_response4  TEXT NOT NULL,
    question_correct1   INTEGER NOT NULL,
    question_correct2   BOOLEAN NOT NULL,
    question_correct3   BOOLEAN NOT NULL,
    question_correct4   BOOLEAN NOT NULL,
    session_name        VARCHAR NOT NULL,
    PRIMARY KEY(question_id),
    FOREIGN KEY(session_name) REFERENCES Session
);