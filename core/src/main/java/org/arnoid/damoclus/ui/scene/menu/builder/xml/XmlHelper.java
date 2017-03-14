package org.arnoid.damoclus.ui.scene.menu.builder.xml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Align;
import org.arnoid.damoclus.ui.scene.menu.builder.model.BaseModel;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class XmlHelper {

    private static final int NO_ALIGN = 0;

    private XmlHelper() {

    }

    public static float readFloatAttribute(XmlPullParser parser, String attributeName, float defaultValue) {
        String sValue = parser.getAttributeValue(null, attributeName);
        if (sValue == null) {
            return defaultValue;
        }
        return Float.parseFloat(sValue);
    }

    public static int readIntAttribute(XmlPullParser parser, String attributeName, int defaultValue) {
        String sValue = parser.getAttributeValue(null, attributeName);
        if (sValue == null) {
            return defaultValue;
        }
        return Integer.parseInt(sValue);
    }

    public static String readStringAttribute(XmlPullParser parser, String attributeName) {
        return parser.getAttributeValue(null, attributeName);
    }

    public static String readStringAttribute(XmlPullParser parser, String attributeName, String defaultValue) {
        String sValue = readStringAttribute(parser, attributeName);
        if (sValue == null) {
            return defaultValue;
        }
        return sValue;
    }

    public static boolean readBooleanAttribute(XmlPullParser parser, String attributeName, boolean defaultValue) {
        String sValue = readStringAttribute(parser, attributeName);
        if (sValue == null) {
            return defaultValue;
        }
        return Boolean.valueOf(sValue);
    }

    public static int readAlignmentAttribute(XmlPullParser parser, String attributeName, int defaultAlignment) {
        String sValue = readStringAttribute(parser, attributeName);
        return calculateAlignment(sValue, defaultAlignment);
    }

    public static FileHandle readFileAttribute(XmlPullParser parser, String attributeName, String filePath) {
        String sValue = parser.getAttributeValue(null, attributeName);
        if (sValue == null) {
            return Gdx.files.internal(filePath);
        }
        return Gdx.files.internal(sValue);
    }

    public static XmlPullParser getXmlParser(FileHandle fileHandle) {
        InputStream is = null;
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            is = fileHandle.read();
            parser.setInput(is, null);

            return parser;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static XmlPullParser getXmlParser(InputStream is) {
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is, null);
            return parser;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasAttribute(XmlPullParser parser, String key) {
        return readStringAttribute(parser, key) != null;
    }

    public static float[] readFloatArrayAttribute(XmlPullParser xmlParser, String key, int length, float defaultVal) {
        String sVal = readStringAttribute(xmlParser, key);
        if (sVal == null) return null;
        String[] splitted = sVal.split(" ");
        float[] result = new float[length];

        for (int i = 0; i < length; i++) {
            if (i >= splitted.length) {
                result[i] = defaultVal;
                continue;
            }
            result[i] = Float.valueOf(splitted[i]);
        }

        return result;
    }

    public static int[] readIntArrayAttribute(XmlPullParser xmlParser, String key, int length, int defaultVal) {
        String sVal = readStringAttribute(xmlParser, key);
        if (sVal == null) return null;
        String[] splitted = sVal.split(" ");
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            if (i >= splitted.length) {
                result[i] = defaultVal;
                continue;
            }
            result[i] = Integer.valueOf(splitted[i]);
        }

        return result;
    }

    public static int calculateAlignment(String alignmentString) {
        return calculateAlignment(alignmentString, Align.left);
    }

    public static int calculateAlignment(String alignmentString, int defaultAlignment) {
        try {
            if (alignmentString == null) {
                // IOS does not catch NullPointerException
                return defaultAlignment;
            }
            alignmentString = alignmentString.toLowerCase();
            String[] alignmentArray = alignmentString.split("\\|");
            int result = NO_ALIGN;
            for (String val : alignmentArray) {
                val = val.trim();
                if ("left".equals(val)) {
                    result |= Align.left;
                } else if ("right".equals(val)) {
                    result |= Align.right;
                } else if ("top".equals(val)) {
                    result |= Align.top;
                } else if ("bottom".equals(val)) {
                    result |= Align.bottom;
                } else if ("center".equals(val)) {
                    result |= Align.center;
                }
            }
            return result == NO_ALIGN ? defaultAlignment : result;
        } catch (Exception e) {
            //ignore
        }
        return defaultAlignment;
    }

    public static BaseModel.Grow calculateGrow(String growString) {
        return calculateGrow(growString, BaseModel.Grow.None);
    }

    public static BaseModel.Grow calculateGrow(String growString, BaseModel.Grow defaultGrow) {
        BaseModel.Grow result = BaseModel.Grow.None;
        try {
            if (growString == null) {
                // IOS does not catch NullPointerException
                return defaultGrow;
            }
            growString = growString.toLowerCase();

            if ("none".equals(growString)) {
                result = BaseModel.Grow.None;
            } else if ("grow".equals(growString)) {
                result = BaseModel.Grow.Grow;
            } else if ("growx".equals(growString)) {
                result = BaseModel.Grow.GrowX;
            } else if ("growy".equals(growString)) {
                result = BaseModel.Grow.GrowY;
            }
        } catch (Exception e) {
            //ignore
        }
        return result;
    }
}