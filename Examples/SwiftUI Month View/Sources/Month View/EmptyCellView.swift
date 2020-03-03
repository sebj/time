//
//  EmptyCellView.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import SwiftUI

struct EmptyCellView: View {
    let size: CGFloat
    
    var body: some View {
        Rectangle()
            .fill(Color(.systemBackground))
            .frame(width: self.size, height: self.size)
    }
}

struct EmptyCellView_Previews: PreviewProvider {
    static var previews: some View {
        EmptyCellView(size: 44)
            .padding()
            .previewLayout(.sizeThatFits)
    }
}
