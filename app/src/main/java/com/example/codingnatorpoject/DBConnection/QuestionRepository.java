package com.example.codingnatorpoject.DBConnection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QuestionRepository {

    static final String[] fields = {"content", "hint", "isOX", "cand1", "cand2", "cand3", "cand4", "answer", "explanation", "image"};
    public static final String id_format = "%d-%d-%d";

    static DatabaseConnector dbc;
    static HashMap<String, HashMap<String, String>> questions;
    static boolean isDownloaded = false;

    public QuestionRepository(Context context) throws JSONException {
        if (!isDownloaded) {
            dbc = new DatabaseConnector(context);
            questions = new HashMap<>();

            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 10; j++) {

                    for (int k = 1, z = (j == 10 ? 10 : 3); k <= z; k++) {
                        String key = String.format(id_format, i, j, k);
                        JSONObject obj = new JSONObject(dbc.getQuestion(i, j, k));
                        int statusCode = obj.getInt("statusCode");

                        if (statusCode != 200) {
                            questions.put(key, null);
                            continue;
                        }

                        questions.put(key, new HashMap<>());
                        obj = obj.getJSONObject("body");

                        HashMap<String, String> tmp = (HashMap) questions.get(key);
                        for (String field : fields) {
                            tmp.put(field, obj.getString(field));
                        }

                    }

                } // end of for(j) - chapter

            } // end of for(i) - stage

            isDownloaded = true;
        }
    }

    // stage, chapter, pn을 전달하면 문제 데이터를 HashMap 타입으로 반환
    // ex) get(1, 1, 1) -> {"content" : "해당 블록은 캐릭터가 바라보는 방향으로 30만큼 움직이나요?", "isOX" : "true", ...}
    public HashMap<String, String> get(int stage, int chapter, int pn) {
        return questions.get(String.format(id_format, stage, chapter, pn));
    }
}
