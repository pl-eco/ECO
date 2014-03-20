package et.visit;

import cs.types.CSBaseType;
import cs.types.CSObjectType;
import cs.types.CSArrayType;
import cs.util.CSUtil;
import et.linersolve.ETValueHolder;
import et.types.etTypeSystem_c;
import polyglot.ast.CanonicalTypeNode;
import polyglot.ast.ClassDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.ArrayType;
import polyglot.types.ClassType;
import polyglot.types.ReferenceType;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class CT2OTPropPass extends TypeChecker {
	ETValueHolder vh;

	public CT2OTPropPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
		vh = ((etTypeSystem_c) ts).getValueHolder();
	}

	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v)
			throws SemanticException {
		final Node m = super.leaveCall(parent, old, n, v);

		if (m instanceof CanonicalTypeNode
				&& ((CanonicalTypeNode) m).type() instanceof ReferenceType) {
			if (parent instanceof ClassDecl) {
				return m;
			}
			/*
			 * record the ET type values
			 */
			ReferenceType type = (ReferenceType) ((CanonicalTypeNode) m).type();

			String alias = null;
			String className = null;
			if (type instanceof ArrayType) {
				CSArrayType arrayType = (CSArrayType) type;
				if (CSUtil.getArrayElementType(arrayType).isPrimitive()) {
					return m;
				}

				alias = arrayType.getAliasName();

				className = ((CSObjectType) CSUtil
						.getArrayElementType(arrayType)).fullClassName();

			} else if (type instanceof CSObjectType) {
				alias = ((CSObjectType) type).getAliasName();

				className = ((CSObjectType) type).fullClassName();
			} else {
				// defensive coding
				assert false;
			}
			vh.computeAliasModifiersFromClassDecl(alias, className);
		}

		return m;
	}
}
