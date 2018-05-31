package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_main {

	public static void main(String[] args) {
	    SpeechToText service = new SpeechToText();
	    service.setUsernameAndPassword("24a3fdd0-e405-49c1-9ba3-860b1b4f96aa", "1iAXxtQJo6cL");

	    File audio = new File("./audio/audio-file.flac");
	    RecognizeOptions options =null;
		try {
			options = new RecognizeOptions.Builder()
					.model("ja-JP_BroadbandModel")
			    .audio(audio)
			    .contentType(RecognizeOptions.ContentType.AUDIO_FLAC)
			    .build();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	    SpeechRecognitionResults transcript = service.recognize(options).execute();

	    JsonNode node=null;
	    ObjectMapper mapper = new ObjectMapper();
	    try {
			 node = mapper.readTree(transcript.toString());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	    System.out.println(transcript);
	    String transcript1= node.get("results").get(0).get("alternatives").get(0).get("transcript").asText();
	    System.out.println(transcript1);
	    Double confidence = node.get("results").get(0).get("alternatives").get(0).get("confidence").asDouble();
	    System.out.println(confidence);

	}

}
