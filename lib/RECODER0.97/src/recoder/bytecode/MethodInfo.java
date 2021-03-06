// This file is part of the RECODER library and protected by the LGPL.

package recoder.bytecode;

import java.util.List;

import recoder.abstraction.ClassType;
import recoder.abstraction.ClassTypeContainer;
import recoder.abstraction.Method;
import recoder.abstraction.Package;
import recoder.abstraction.Type;
import recoder.convenience.Naming;

public class MethodInfo extends MemberInfo implements Method {

    protected String[] paramtypes;

    protected String returntype;

    protected String[] exceptions;
    
    protected AnnotationUseInfo paramAnnotations[][];
    
    protected List<TypeArgumentInfo> paramTypeArgs[];
    
    protected List<TypeParameterInfo> typeParms;
    
    public MethodInfo(int accessFlags, String returntype, boolean returnTypeIsTypeVariable, String name, String[] paramtypes, String[] exceptions,
            ClassFile cf) {
        super(accessFlags, name, cf, returnTypeIsTypeVariable);
        if (returntype != null) {
            this.returntype = returntype.intern();
        }
        this.paramtypes = paramtypes;
        for (int i = 0; i < paramtypes.length; i++) {
            paramtypes[i] = paramtypes[i].intern();
        }
        this.exceptions = exceptions;
        if (exceptions != null) {
            for (int i = 0; i < exceptions.length; i++) {
                exceptions[i] = exceptions[i].intern();
            }
        }
    }

    public final String[] getParameterTypeNames() {
        return paramtypes;
    }
    
    public final AnnotationUseInfo[] getAnnotationsForParam(int paramNum) {
    	if (paramAnnotations == null)
    		return null;
    	return paramAnnotations[paramNum];
    }
    
    public final List<TypeArgumentInfo> getTypeArgumentsForParam(int paramNum) {
    	if (paramTypeArgs == null)
    		return null;
    	return paramTypeArgs[paramNum];
    }
    
    public final List<TypeArgumentInfo> getTypeArgumentsForReturnType() {
    	if (paramTypeArgs == null)
    		return null;
    	return paramTypeArgs[paramTypeArgs.length-1];
    }

    public final String[] getExceptionsInfo() {
        return exceptions;
    }

    public final String getTypeName() {
        return returntype;
    }

    public Type getReturnType() {
        return service.getReturnType(this);
    }

    public List<Type> getSignature() {
        return service.getSignature(this);
    }

    public List<ClassType> getExceptions() {
        return service.getExceptions(this);
    }

    public ClassTypeContainer getContainer() {
        return getContainingClassType();
    }

    public Package getPackage() {
        return service.getPackage(this);
    }

    public List<? extends ClassType> getTypes() {
        return service.getTypes(this);
    }

    public String getFullName() {
        return Naming.getFullName(this);
    }
    
	public String getBinaryName() {
		return getContainingClassType().getBinaryName() + "." + getName();
	}

    
    public boolean isVarArgMethod() {
        return (accessFlags & VARARGS) != 0;
    }

	public List<TypeParameterInfo> getTypeParameters() {
		return typeParms;
	}
	
	@Override
	public String toString() {
		return "%BC%" + getName() + "(" + getSignature().size() + ")";
	}
 
	@Override
	public MethodInfo getGenericMember() {
		return this;
	}

	@Override
	public String getFullSignature() {
		return Method.SignatureBuilder.buildSignature(this);
	}
}