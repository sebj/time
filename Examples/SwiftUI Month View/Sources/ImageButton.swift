//
//  ImageButton.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import SwiftUI

struct ImageButton: View {
    let action: () -> Void
    let imageSystemName: String
    var size: CGFloat = 28
    
    var body: some View {
        Button(
            action: action,
            label: {
                Image(systemName: imageSystemName)
                    .resizable()
                    .frame(width: size, height: size)
                    .padding()})
    }
}

// MARK: - Previews

struct ImageButton_Previews: PreviewProvider {
    static var previews: some View {
        ImageButton(action: {}, imageSystemName: "chevron.left.circle.fill")
            .padding()
            .previewLayout(.sizeThatFits)
    }
}
