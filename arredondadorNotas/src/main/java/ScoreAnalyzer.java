import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScoreAnalyzer {

  static int MIN_SCORE = 38;
  static int ROUND_UP_MULTIPLE = 5;

  public static void main(String[] args) {

    try {
      List<String> lines = Files.readAllLines(Paths.get(
          "/Users/lucasfranco/Documents/projetos/idtrust/arredondadorNotas/src/main/resources/notas.txt"));


      int numOfStudents  = Integer.parseInt(lines.remove(0));

      List<String> scoresAnalyzed = new ArrayList<>();
      for ( int i=0; i<numOfStudents; i++)
        scoresAnalyzed.add(analyzeScore(lines.get(i)));

    Files.write(Paths.get("result.txt"), scoresAnalyzed, StandardCharsets.UTF_8);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static String analyzeScore(String line) {
    Integer score = Integer.parseInt(line);

    if (score >= ScoreAnalyzer.MIN_SCORE) {
      return roundUpScore(score).toString();
    }

    return score.toString();
  }

  public static Integer roundUpScore(Integer score) {
    Integer nextMultiple =
        ((score.intValue() / ScoreAnalyzer.ROUND_UP_MULTIPLE) * ScoreAnalyzer.ROUND_UP_MULTIPLE)
            + ScoreAnalyzer.ROUND_UP_MULTIPLE;

    if ((nextMultiple - score) < 3) {
      score = nextMultiple;
    }

    return score;
  }
}






