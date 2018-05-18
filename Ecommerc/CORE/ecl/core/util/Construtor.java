package ecl.core.util;

public class Construtor {
	
	private String sqlSelect;
	private String sqlUpdate;
	private String sqlWhere;
	
	public Construtor() {
		sqlWhere = "";
	}
	public void Construir(String novo) {
		if(sqlWhere.equals("")) {
		 sqlWhere = novo;	
		}else
		sqlWhere = sqlWhere + " AND " + novo;
	}
	public void ConstruirSomente(String novo) {
		if(sqlWhere.equals("")) {
		 sqlWhere = novo;	
		}else
		sqlWhere = sqlWhere + novo;
	}
	
	public void ConstruirOU(String novo) {
		if(sqlWhere.equals("")) {
		 sqlWhere = novo;	
		}else
		sqlWhere = sqlWhere + " OR " + novo;
	}
	
	public void ConstruirParentAbre() {
		if(sqlWhere.equals("")) {	
		}else
		sqlWhere = sqlWhere + " ( " ;
	}
	
	public void ConstruirParentFecha() {
		if(sqlWhere.equals("")) {
		}else
		sqlWhere = sqlWhere + " ) " ;
	}
	
	public void ConstruirAND() {
		if(sqlWhere.equals("")) {
		}else
		sqlWhere = sqlWhere + " AND " ;
	}
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public String getSqlSelect() {
		return sqlSelect;
	}

	public void setSqlSelect(String sqlSelect) {
		this.sqlSelect = sqlSelect;
	}

	public String getSqlUpdate() {
		return sqlUpdate;
	}

	public void setSqlUpdate(String sqlUpdate) {
		this.sqlUpdate = sqlUpdate;
	}

}
