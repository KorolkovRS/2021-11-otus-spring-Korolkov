package ru.korolkovrs.spring.converter;

import org.junit.jupiter.api.*;
import ru.korolkovrs.spring.domain.Answer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QuestionToStringConverter")
public class TextToAnswerConverterTest {
    static TextToAnswerConverter converter;

    @BeforeAll
    static void init() {
        converter = new TextToAnswerConverter();
    }

    @DisplayName("Correct convert text to answer")
    @Test
    void shouldCorrectConvertTextToAnswer() {
        Answer trueAnswer = new Answer();
        trueAnswer.setName("One, two; three?");
        trueAnswer.setCorrect(true);
        String trueAnswerString = "One, two; three?:1";

        Answer falseAnswer = new Answer();
        falseAnswer.setName("One, two; three?");
        falseAnswer.setCorrect(false);
        String falseAnswerString = "One, two; three?:0";

        assertAll(
                () -> assertEquals(trueAnswer, converter.convertToRead(trueAnswerString)),
                () -> assertEquals(falseAnswer, converter.convertToRead(falseAnswerString))
        );
    }

    @Test
    @DisplayName("Throws an error if column contain values other than 1 or 0")
    void shouldTrowsExceptionIfContainInvalidValues() {
        String incorrectString = "One, two; three?:3";
        assertThrows(RuntimeException.class, () -> converter.convertToRead(incorrectString));
    }
}
