public protocol Alsoable {}

extension Alsoable {
    @discardableResult
    @inline(__always)
    public func also(_ block: (Self) throws -> Void) rethrows -> Self {
        try block(self)

        return self
    }
}

protocol ApplyableReference: AnyObject {}

extension ApplyableReference {
    @discardableResult
    @inline(__always)
    public func apply(_ block: (Self) throws -> Void) rethrows -> Self {
        try block(self)

        return self
    }
}

public protocol ApplyableValue {}

extension ApplyableValue {
    @discardableResult
    @inline(__always)
    public func applied(_ block: (inout Self) throws -> Void) rethrows -> Self {
        var copy: Self = self

        try block(&copy)

        return copy
    }
}
