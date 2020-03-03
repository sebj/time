//
//  ContentView.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject
    private var viewModel = ContentViewModel()
    
    private var monthNavigationView: some View {
        HStack(spacing: 0) {
            ImageButton(
                action: self.viewModel.selectPreviousMonth,
                imageSystemName: "chevron.left.circle.fill")
                .accessibility(label: Text("Previous month"))
            
            Spacer()
            
            ImageButton(
                action: self.viewModel.selectNextMonth,
                imageSystemName: "chevron.right.circle.fill")
                .accessibility(label: Text("Next month"))
        }
        .accentColor(.primary)
    }
    
    var body: some View {
        NavigationView {
            VStack(spacing: 0) {
                self.monthNavigationView
                
                MonthGridView(viewModel: self.viewModel.gridViewModel)
                
                Spacer()
            }
            .padding(.horizontal)
            .navigationBarTitle(self.viewModel.title)
        }
        .onAppear(perform: self.viewModel.onAppear)
    }
}

// MARK: - Previews

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
