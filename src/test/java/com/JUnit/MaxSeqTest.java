package com.JUnit;
import com.curr.MaxSeq;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class MaxSeqTest {

  static Stream<TestData> inputProvider() {
    return Stream.of(
      new TestData(Arrays.asList(1,2,5,8,8,8,5,5,5,5,9,10,13,5,5,5), 4, 5),
      new TestData(Arrays.asList(), -1, -1),
      new TestData(Arrays.asList(7,7,7,7), 4, 7),
      new TestData(Arrays.asList(1,2,3,4), 1, 1)
    );
  }

  @ParameterizedTest
  @MethodSource("inputProvider")
  void testMaxSeq(TestData data) {
    MaxSeq.Result result = MaxSeq.findMaxSeq(new ArrayList<>(data.input));
    Assertions.assertEquals(data.expectedLength, result.length);
    Assertions.assertEquals(data.expectedValue, result.value);
  }

  static class TestData {
    List<Integer> input;
    int expectedLength;
    int expectedValue;
    TestData(List<Integer> input, int expectedLength, int expectedValue) {
      this.input = input;
      this.expectedLength = expectedLength;
      this.expectedValue = expectedValue;
    }
  }
}
