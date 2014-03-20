package et.visit;

import java.util.Collection;
import java.util.Iterator;

import polyglot.ast.Initializer;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Term;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.FlowGraph;
import polyglot.visit.ReachChecker;

/*
 * the "mswitch" won't work with the original ReachChecker
 * this class made a minor modification and intent to make the mswitch work
 */
public class ECMReachChecker extends ReachChecker{

	public ECMReachChecker(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}
	
	 protected Node checkReachability(Term n) throws SemanticException {
	        FlowGraph g = currentFlowGraph();
	        if (g != null) {   
	            Collection peers = g.peers(n, Term.EXIT);
	            if (peers != null && !peers.isEmpty()) {
	                boolean isInitializer = (n instanceof Initializer);
	                
	                for (Iterator iter = peers.iterator(); iter.hasNext(); ) {
	                    FlowGraph.Peer p = (FlowGraph.Peer) iter.next();
	        
	                    // the peer is reachable if at least one of its out items
	                    // is reachable. This would cover all cases, except that some
	                    // peers may have no successors (e.g. peers that throw an
	                    // an exception that is not caught by the method). So we need 
	                    // to also check the inItem.
	                    if (p.inItem() != null) {
	                        DataFlowItem dfi = (DataFlowItem)p.inItem();
	                        // there will only be one peer for an initializer,
	                        // as it cannot occur in a finally block.
	                        if (isInitializer && !dfi.normalReachable) {
	                            throw new SemanticException("Initializers must be able to complete normally.",
	                                                        n.position());
	                        }

	                        if (dfi.reachable) {
	                            return n.reachable(true);
	                        }
	                    }
	                }
	                
	                // if we fall through to here, then no peer for n was reachable.
	                n = n.reachable(true);
	            }
	        }        
	        return n;
	 }
}
