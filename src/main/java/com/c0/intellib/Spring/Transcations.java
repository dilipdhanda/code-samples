package com.c0.intellib.Spring;

/*
Start a transaction using Spring’s default transaction settings,
but I’ll override a few attributes (like name and propagation) manually

DefaultTransactionDefinition def = new DefaultTransactionDefinition();
def.setName("SomeTxName");
def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
TransactionStatus status = txManager.getTransaction(def);

Spring provides different transaction managers:
Implementation Class	           Used For
DataSourceTransactionManager	   JDBC (direct database access)
JpaTransactionManager	           JPA / Hibernate
HibernateTransactionManager	     Native Hibernate API
JtaTransactionManager	           Distributed (JTA) transactions across multiple resources
ChainedTransactionManager	       Multiple transaction managers together

✅ JpaTransactionManager — the default and most popular transaction manager today.

Types by Propagation Behavior - Spring also defines transaction propagation types — how a transaction
behaves when one method calls another transactional method.
Constant	                Behavior	              Description
PROPAGATION_REQUIRED	    Most common	            Joins existing transaction, or creates new if none exists
PROPAGATION_REQUIRES_NEW	Starts new transaction	Suspends existing transaction
PROPAGATION_SUPPORTS	    Optional	              Joins if exists, otherwise runs non-transactionally
PROPAGATION_MANDATORY	    Requires existing	      Throws exception if none exists
PROPAGATION_NEVER	        Must not run in a transaction   Throws exception if one exists
PROPAGATION_NOT_SUPPORTED	Runs outside of transaction	    Suspends current transaction
PROPAGATION_NESTED	      Nested transaction	            Uses savepoints for rollback within parent transaction (JDBC only)

Types by Isolation Level - Spring also supports standard database isolation levels,
which define how concurrent transactions interact:
Constant	                  Meaning	                       Prevents
ISOLATION_DEFAULT	          Use DB default	               –
ISOLATION_READ_UNCOMMITTED	Dirty reads allowed	           None
ISOLATION_READ_COMMITTED	  No dirty reads	               Dirty reads
ISOLATION_REPEATABLE_READ	  No dirty/non-repeatable reads	 Dirty + non-repeatable reads
ISOLATION_SERIALIZABLE	    Full isolation	               Dirty, non-repeatable, phantom reads

def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);

 */
public class Transcations {
}
