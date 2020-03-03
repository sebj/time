//
//  View+AnyView.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import SwiftUI

extension View {
    func erasedToAnyView() -> AnyView {
        AnyView(self)
    }
}
