/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 * 
 * http://www.weixin4j.org/sdk/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.weixin4j.http;

import org.weixin4j.WeixinException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * @author weixin4j<weixin4j@ansitech.com>
 * @version 1.0
 */
public class Response {

    private HttpsURLConnection con;
    private int status;
    private InputStream is;
    private String responseAsString = null;
    private boolean streamConsumed = false;

    public Response() {
    }

    public Response(HttpsURLConnection con) throws IOException {
        this.con = con;
        this.status = con.getResponseCode();
        if (null == (is = con.getErrorStream())) {
            is = con.getInputStream();
        }
    }

    /**
     * Returns the response stream.<br> This method cannot be called after
     * calling asString() or asDcoument()<br> It is suggested to call
     * disconnect() after consuming the stream.
     *
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body stream
     * @throws WeixinException
     * @see #disconnect()
     */
    public InputStream asStream() {
        if (streamConsumed) {
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return is;
    }

    /**
     * 将输出流转换为String字符串
     *
     * @return 输出内容
     * @throws WeixinException
     */
    public String asString() throws WeixinException {
        if (null == responseAsString) {
            BufferedReader br;
            try {
                InputStream stream = asStream();
                if (null == stream) {
                    return null;
                }
                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                StringBuilder buf = new StringBuilder();
                String line;
                while (null != (line = br.readLine())) {
                    buf.append(line).append("\n");
                }
                this.responseAsString = buf.toString();
                stream.close();
                con.disconnect();
                streamConsumed = true;
            } catch (NullPointerException npe) {
                // don't remember in which case npe can be thrown
                throw new WeixinException(npe.getMessage(), npe);
            } catch (IOException ioe) {
                throw new WeixinException(ioe.getMessage(), ioe);
            }
        }
        return responseAsString;
    }

    /**
     * 将输出流转换为JSON对象
     *
     * @return JSONObject对象
     */
    public JSONObject asJSONObject() throws WeixinException {
        return JSONObject.fromObject(asString());
    }

    /**
     * 将输出流转换为JSON对象
     *
     * @return JSONArray对象
     */
    public JSONArray asJSONArray() throws WeixinException {
        return JSONArray.fromObject(asString());
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
}
