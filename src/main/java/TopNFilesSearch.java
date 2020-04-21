import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class TopNFilesSearch {
    /**
     * Find the top N largest file in the @rootPath or subdirectory of it.
     *
     * @param rootPath
     * @param numberOfFiles number of top files
     * @return array of top N Files.
     */
    File[] topNLargestFiles(File rootPath, int numberOfFiles) {
        if (rootPath == null || !rootPath.isDirectory()) {
            return null;
        }

        File[] topN = new File[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            topN[i] = null;
        }
        Queue<File> queue = new LinkedList<>();
        queue.add(rootPath);

        while (!queue.isEmpty()) {
            File node = queue.remove();
            File[] children = node.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isDirectory()) {
                        queue.add(child);
                    } else {
                        topN = addToTopN(topN, child);
                    }
                }
            }
        }
        return topN;
    }

    /**
     * Add the node to topN if fits in top N records.
     *
     * @param topN
     * @param node
     * @return topN files.
     */
    private File[] addToTopN(File[] topN, File node) {
        if (topN[topN.length - 1] != null && topN[topN.length - 1].length() >= node.length()) {
            return topN;
        }
        for (int i = 0; i < topN.length; i++) {
            if (topN[i] == null) {
                topN[i] = node;
                return topN;
            } else if (topN[i].length() < node.length()) {
                File temp = topN[i];
                topN[i] = node;
                node = temp;
            }
        }
        return topN;
    }


    public static void main(String[] args) throws IOException {
        TopNFilesSearch searchTopN = new TopNFilesSearch();
        File[] topNLargestFiles = searchTopN.topNLargestFiles(new File("/Users/gnaderi/Downloads"), 10);
        for (File file : topNLargestFiles) {
            System.out.println(file.getName() + "=" + Files.size(Paths.get(file.getPath())));
        }
    }
}
