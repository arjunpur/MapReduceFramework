package DFS;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import Util.Tuple;

public interface DataNodeInterface extends Remote {
	public void setActive(boolean bool) throws RemoteException;
	public void start() throws RemoteException;
	public void initiateBlock(File block_file, DFSBlock file_block) throws RemoteException;
	public String getHeartbeatHelperID() throws RemoteException;
	public HashMap<Tuple<String,Integer>,File> getBlockFileMap() throws RemoteException;
	public HashMap<String,List<DFSBlock>> getFileBlockReplicaMap() throws RemoteException;
	public void exitDataNode() throws RemoteException;
	public String getNodeId() throws RemoteException;
}