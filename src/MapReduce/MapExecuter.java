package MapReduce;

import IOFormat.KeyValuePair;
import IOFormat.MapperCollector;
import com.sun.xml.internal.ws.util.QNameMap;

import java.io.*;
import java.util.*;
import java.util.Map;

/**
 * Created by karansharma on 11/19/14.
 */
public class MapExecuter implements Runnable {

    private TaskManager taskManager;
    private String jobID;
    private Mapper mapper;
    private String inputFilePath;

    public MapExecuter(TaskManager taskManager, String jobID, Mapper mapper, String inputFilePath)
    {
        this.taskManager = taskManager;
        this.jobID = jobID;
        this.mapper = mapper;
        this.inputFilePath = inputFilePath;
    }

    public void run()
    {
        /* READ AND MAP */

        /* open reader */
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inputFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /* Create collecter and execute user map on each line of input file */
        MapperCollector collector = new MapperCollector();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                mapper.map(line,collector);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Setup Intermediate Map Output File */
        String outFilePath = inputFilePath.replace(".txt","out.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outFilePath, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Get SortedMap containing map output */
       ArrayList<KeyValuePair> mapOutput = collector.getMapperOutputCollection();

        /* Write each key value pair to file */
        if(writer == null) {
            //TODO: FAILURE HANDLING
            return;
        }

        for(KeyValuePair kvp : mapOutput) {
            writer.println(kvp.toString());
        }
        writer.close();

        /* Notify task manager of completion and perform reduce if necessary */
        taskManager.checkReduce(jobID, outFilePath);
    }

}
