import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Connection {
    private static Connection instance = null;

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    private JSONObject converter(HttpURLConnection conn) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     *
     * @param problemId
     * @return authKey
     */
    public String start(int problemId) {
        HttpURLConnection conn = null;

        String response = null;
        String param = "?problem=" + problemId;

        try {
            URL url = new URL(BaseData.baseUrl + BaseData.start + param);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-Auth-Token", BaseData.xAuthToken);
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                JSONObject jsonObject = converter(conn);
                response = jsonObject.getString("auth_key");
            } else {
                response = String.valueOf(responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Get request, 각 자전거 대여소가 보유한 자전거 수 반환
     * @param authKey
     * @return JSONObject locations
     */
    public JSONObject locations(String authKey) {
        HttpURLConnection conn = null;
        JSONObject jsonObject = null;
        try {
            URL url = new URL(BaseData.baseUrl + BaseData.locations);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", authKey);
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                jsonObject = converter(conn);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    public JSONObject trucks(String authKey) {
        HttpURLConnection conn = null;
        JSONObject jsonObject = null;
        try {
            URL url = new URL(BaseData.baseUrl + BaseData.trucks);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", authKey);
            conn.setRequestProperty("Content-Type", "application/json");
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                jsonObject = converter(conn);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    public JSONObject simulate(String authKey, ArrayList<Command> commands) {
        HttpURLConnection conn = null;
        JSONObject jsonObject = null;
        JSONObject obj = new JSONObject();

        try {
            URL url = new URL(BaseData.baseUrl + BaseData.simulate);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Authorization", authKey);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < commands.size(); i++) {
                JSONObject command = new JSONObject();
                command.put("truck_id", commands.get(i).getTruckId());
                command.put("command", commands.get(i).getCommand());
                jsonArray.put(command);
            }

            obj.put("commands", jsonArray);

            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(obj.toString());
            bw.flush();
            bw.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                jsonObject = converter(conn);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public float score(String authKey) {
        HttpURLConnection conn = null;
        JSONObject jsonObject = null;
        float score = 0;
        try {
            URL url = new URL(BaseData.baseUrl + BaseData.score);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", authKey);
            conn.setRequestProperty("Content-Type", "application/json");
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                jsonObject = converter(conn);
                score = jsonObject.getFloat("score");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return score;
    }


}
