import SwiftUI

extension View {
    @discardableResult
    @inline(__always)
    public func also(_ block: (Self) throws -> Void) rethrows -> Self {
        try block(self)

        return self
    }

    @discardableResult
    @inline(__always)
    public func applied(_ block: (inout Self) throws -> Void) rethrows -> Self {
        var copy: Self = self

        try block(&copy)

        return copy
    }

    @discardableResult
    @inline(__always)
    public func `let`<T>(_ block: (Self) throws -> T) rethrows -> T {
        try block(self)
    }

    @discardableResult
    @inline(__always)
    public func run<T: View>(@ViewBuilder _ block: (Self) throws -> T) rethrows -> T {
        try block(self)
    }
}
