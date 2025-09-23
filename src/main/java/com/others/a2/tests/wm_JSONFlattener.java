package com.others.a2.tests;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class wm_JSONFlattener { // CG - original Walmart - Chandra - 02/02/24 through Flexton

    public static Map<String, Object> flatten(JSONObject jsonObject) {
        Map<String, Object> flatMap = new HashMap<>();
        flattenRecursive("", jsonObject, flatMap);
        return flatMap;
    }

    private static void flattenRecursive(String prefix, JSONObject jsonObject, Map<String, Object> flatMap) {
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = jsonObject.get(key);

            String newPreFix = prefix.isEmpty() ? key : prefix + "." + key;
            if (value instanceof JSONObject) {
                // recursive calls until you get to leaf node (a HashMap), that is not a JSONObject instance
                flattenRecursive(newPreFix, (JSONObject) value, flatMap);
            } else {
                flatMap.put(newPreFix, value);
            }
        }
    }

    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John\",\"address\":{\"city\":\"New York\",\"zip\":\"10001\"},\"contact\":{\"phone\":{\"home\":\"1234567890\",\"work\":\"0987654321\"}}}";

        JSONObject jsonObject = new JSONObject(jsonString);
        Map<String, Object> flatMap = flatten(jsonObject);

        for (Map.Entry<String, Object> entry : flatMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

/*
Walmart - Chandra asked for JSON Flattener, you coded a tree Flattener,
even that had issue 02/02/24 Walmart Interview through Flexton

public class JSON_Flatten_code_ex {  // was named Walmart_Flatten_json_code_ex
    public void flatten(TreeNode root) { // add previous dot concatenated string
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null){
            // TODO print add previous dot concatenated string with value below
            System.out.println(root.val);
        }
        if (root.left != null) {
            flatten(root.left);
        }
        if (root.right != null) {
            flatten(root.right);
        }
    }

    TreeNode getInputTree(){

//       1
//         2
//            3
//            4
//         5
//            null
//            6

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        return root;
    }

    public static void main(String[] args) {
        JSON_Flatten_code_ex flatten = new JSON_Flatten_code_ex();
        flatten.flatten(flatten.getInputTree());
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
 */
