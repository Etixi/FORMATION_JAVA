package com.infotel.eshop.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Script {
    private Supplier<Connection> connectionSupplier;
    private Path path;
    
    public Script(Path path, Supplier<Connection> connectionSupplier) {
		super();
		this.path = path;
		this.connectionSupplier = connectionSupplier;
	}
    
    public void execute() throws SQLException, IOException {
    	List<String> statements = getStatements();
    	
		try (
			Connection cn = connectionSupplier.get();
			Statement st = cn.createStatement();
		) {
			cn.setAutoCommit(false);
				
			for(String sql : statements){
				st.addBatch(sql);
			}
			st.executeBatch();
			cn.commit();		
		}	
    }
    
    private String getContent() throws IOException {
    	return Files.readString(path);
    }

	private List<String> getStatements() throws IOException {
    	List<String> statements = new ArrayList<>();
    	String[] lines = getContent().split("\\R");
    	
    	StringBuilder sb = new StringBuilder();
    	for (String line : lines) {
    		String l = line.strip();
			if (l.startsWith("--") || line.isBlank()) {
				continue;
			}
			
			sb.append(l);
			
			if (l.endsWith(";")) {
				sb.deleteCharAt(sb.length() - 1); // remove ';' char
				String sql = sb.toString().strip();
				statements.add(sql);
				sb.setLength(0);
				continue;
			}

			sb.append(" ");
		}
    	
    	return statements;
    }
}
